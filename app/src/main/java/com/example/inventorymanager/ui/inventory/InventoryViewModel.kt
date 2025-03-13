@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val materialDao: MaterialDao
) : ViewModel() {

    private val _filterType = MutableStateFlow(FilterType.ALL)
    
    val inventory = _filterType.flatMapLatest { type ->
        when (type) {
            FilterType.ALL -> materialDao.getAllMaterials()
            FilterType.LOW_STOCK -> materialDao.getLowStockMaterials()
        }.map { UiState.Success(it) }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UiState.Loading
    )

    fun setFilter(type: FilterType) {
        _filterType.value = type
    }

    fun addMaterial(material: Material) {
        viewModelScope.launch {
            materialDao.insertMaterial(material)
        }
    }

    fun updateMaterial(material: Material) {
        viewModelScope.launch {
            materialDao.updateMaterial(material)
        }
    }

    fun deleteMaterial(material: Material) {
        viewModelScope.launch {
            materialDao.deleteMaterial(material)
        }
    }
}

enum class FilterType {
    ALL, LOW_STOCK
}
