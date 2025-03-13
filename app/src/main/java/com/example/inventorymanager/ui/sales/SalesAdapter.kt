class SalesAdapter : ListAdapter<Sale, SalesAdapter.SaleViewHolder>(SaleDiffCallback()) {

    var onItemClick: ((Sale) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleViewHolder {
        val binding = ItemSaleBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SaleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SaleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SaleViewHolder(
        private val binding: ItemSaleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
            }
        }

        fun bind(sale: Sale) {
            binding.apply {
                orderNumber.text = sale.orderNumber
                customerName.text = sale.customerName
                totalAmount.text = itemView.context.getString(
                    R.string.price_format,
                    sale.totalAmount
                )
                date.text = SimpleDateFormat(
                    "dd MMM yyyy, HH:mm",
                    Locale.getDefault()
                ).format(Date(sale.date))
            }
        }
    }

    private class SaleDiffCallback : DiffUtil.ItemCallback<Sale>() {
        override fun areItemsTheSame(oldItem: Sale, newItem: Sale) = 
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Sale, newItem: Sale) = 
            oldItem == newItem
    }
} 