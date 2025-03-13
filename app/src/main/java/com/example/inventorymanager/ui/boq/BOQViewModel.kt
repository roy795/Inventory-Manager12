@HiltViewModel
class BOQViewModel @Inject constructor(
    private val repository: BOQRepository
) : ViewModel() {
    private val _currentProject = MutableStateFlow<String?>(null)
    val currentProject: StateFlow<String?> = _currentProject.asStateFlow()

    val boqItems: StateFlow<List<BOQ>> = _currentProject
        .flatMapLatest { project ->
            project?.let {
                repository.getBOQsByProject(it)
            } ?: repository.getAllBOQs()
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val projectTotal: StateFlow<Double> = _currentProject
        .flatMapLatest { project ->
            project?.let {
                repository.getProjectTotal(it)
            } ?: flow { emit(0.0) }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, 0.0)

    fun setCurrentProject(projectName: String?) {
        _currentProject.value = projectName
    }

    fun addBOQItem(
        projectName: String,
        itemDescription: String,
        quantity: Double,
        unit: String,
        unitPrice: Double
    ) {
        viewModelScope.launch {
            val boq = BOQ(
                projectName = projectName,
                itemDescription = itemDescription,
                quantity = quantity,
                unit = unit,
                unitPrice = unitPrice,
                totalAmount = quantity * unitPrice
            )
            repository.addBOQItem(boq)
        }
    }
} 