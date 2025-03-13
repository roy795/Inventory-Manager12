@AndroidEntryPoint
class ProductionReportFragment : Fragment() {
    private var _binding: FragmentProductionReportBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: ProductionReportViewModel by viewModels()
    private val adapter = ProductionReportAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductionReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeProductionData()
    }

    private fun setupRecyclerView() {
        binding.productionRecyclerView.apply {
            adapter = this@ProductionReportFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeProductionData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.productionMetrics.collect { metrics ->
                binding.productionMetrics.setData(
                    "Production Output",
                    metrics.monthlyOutput,
                    metrics.weeklyOutput
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
