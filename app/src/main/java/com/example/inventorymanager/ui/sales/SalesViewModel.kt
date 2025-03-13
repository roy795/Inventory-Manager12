@HiltViewModel
class SalesViewModel @Inject constructor(
    private val repository: SalesRepository
) : ViewModel() {

    val sales = repository.getAllSales()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val totalSales = repository.getTotalSales()
        .stateIn(viewModelScope, SharingStarted.Lazily, 0.0)

    fun createSale(sale: Sale, items: List<SaleItem>) {
        viewModelScope.launch {
            repository.createSale(sale, items)
        }
    }
}
