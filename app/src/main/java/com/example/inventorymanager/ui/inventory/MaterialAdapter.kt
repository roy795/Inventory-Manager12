class MaterialAdapter : ListAdapter<Material, MaterialAdapter.MaterialViewHolder>(MaterialDiffCallback()) {

    var onItemClick: ((Material) -> Unit)? = null
    var onMenuClick: ((Material, View) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialViewHolder {
        val binding = ItemMaterialBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MaterialViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MaterialViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MaterialViewHolder(
        private val binding: ItemMaterialBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
            }
            binding.menuButton.setOnClickListener {
                onMenuClick?.invoke(getItem(bindingAdapterPosition), it)
            }
        }

        fun bind(material: Material) {
            binding.apply {
                materialName.text = material.name
                quantityText.text = itemView.context.getString(
                    R.string.quantity_format,
                    material.quantity,
                    material.unit
                )
                priceText.text = itemView.context.getString(
                    R.string.price_format,
                    material.price
                )

                // Highlight low stock items
                val isLowStock = material.quantity <= material.minQuantity
                root.setCardBackgroundColor(
                    if (isLowStock) 
                        ContextCompat.getColor(root.context, R.color.low_stock_background)
                    else 
                        ContextCompat.getColor(root.context, R.color.card_background)
                )
            }
        }
    }

    private class MaterialDiffCallback : DiffUtil.ItemCallback<Material>() {
        override fun areItemsTheSame(oldItem: Material, newItem: Material) = 
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Material, newItem: Material) = 
            oldItem == newItem
    }
} 