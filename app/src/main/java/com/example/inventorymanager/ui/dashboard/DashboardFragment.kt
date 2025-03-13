@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    
    private val inventoryViewModel: InventoryViewModel by viewModels()
    private val salesViewModel: SalesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModels()
    }

    private fun observeViewModels() {
        viewLifecycleOwner.lifecycleScope.launch {
            salesViewModel.totalSales.collect { total ->
                binding.totalSalesValue.text = getString(R.string.price_format, total)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            inventoryViewModel.inventory.collect { state ->
                when (state) {
                    is UiState.Success -> {
                        val lowStockCount = state.data.count { it.quantity <= it.minQuantity }
                        binding.lowStockValue.text = lowStockCount.toString()
                    }
                    else -> binding.lowStockValue.text = "-"
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
