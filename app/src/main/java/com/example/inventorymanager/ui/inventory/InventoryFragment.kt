@AndroidEntryPoint
class InventoryFragment : Fragment() {

    private var _binding: FragmentInventoryBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: InventoryViewModel by viewModels()
    private val materialAdapter = MaterialAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInventoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupListeners()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.materialsRecyclerView.apply {
            adapter = materialAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupListeners() {
        binding.fabAddMaterial.setOnClickListener {
            findNavController().navigate(
                R.id.action_inventoryFragment_to_addMaterialFragment
            )
        }

        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, _ ->
            when (group.checkedChipId) {
                R.id.chipAll -> viewModel.setFilter(FilterType.ALL)
                R.id.chipLowStock -> viewModel.setFilter(FilterType.LOW_STOCK)
            }
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.inventory.collect { state ->
                when (state) {
                    is UiState.Loading -> showLoading()
                    is UiState.Success -> showContent(state.data)
                    is UiState.Error -> showError(state.message)
                }
            }
        }
    }

    private fun showLoading() {
        binding.apply {
            loadingView.root.isVisible = true
            materialsRecyclerView.isVisible = false
            errorView.root.isVisible = false
        }
    }

    private fun showContent(materials: List<Material>) {
        binding.apply {
            loadingView.root.isVisible = false
            materialsRecyclerView.isVisible = true
            errorView.root.isVisible = false
            materialAdapter.submitList(materials)
        }
    }

    private fun showError(message: String) {
        binding.apply {
            loadingView.root.isVisible = false
            materialsRecyclerView.isVisible = false
            errorView.root.isVisible = true
            errorView.errorMessage.text = message
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
