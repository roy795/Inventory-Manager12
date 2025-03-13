@AndroidEntryPoint
class AddMaterialFragment : Fragment() {
    private var _binding: FragmentAddMaterialBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: InventoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddMaterialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.saveButton.setOnClickListener {
            if (validateInputs()) {
                saveMaterial()
            }
        }
    }

    private fun validateInputs(): Boolean {
        var isValid = true
        
        binding.apply {
            if (nameInput.text.isNullOrBlank()) {
                nameLayout.error = getString(R.string.error_required_field)
                isValid = false
            }
            
            if (quantityInput.text.isNullOrBlank()) {
                quantityLayout.error = getString(R.string.error_required_field)
                isValid = false
            }
            
            if (unitInput.text.isNullOrBlank()) {
                unitLayout.error = getString(R.string.error_required_field)
                isValid = false
            }
            
            if (priceInput.text.isNullOrBlank()) {
                priceLayout.error = getString(R.string.error_required_field)
                isValid = false
            }
        }
        
        return isValid
    }

    private fun saveMaterial() {
        val material = Material(
            name = binding.nameInput.text.toString(),
            quantity = binding.quantityInput.text.toString().toDoubleOrNull() ?: 0.0,
            minQuantity = binding.minQuantityInput.text.toString().toDoubleOrNull() ?: 0.0,
            unit = binding.unitInput.text.toString(),
            price = binding.priceInput.text.toString().toDoubleOrNull() ?: 0.0
        )

        viewModel.addMaterial(material)
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 