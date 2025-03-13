@AndroidEntryPoint
class SalesFragment : Fragment() {
    private var _binding: FragmentSalesBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: SalesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSalesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeSales()
    }

    private fun setupViews() {
        binding.fabAddSale.setOnClickListener {
            findNavController().navigate(R.id.action_sales_to_new_sale)
        }
    }

    private fun observeSales() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.sales.collect { sales ->
                // Update UI with sales data
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
