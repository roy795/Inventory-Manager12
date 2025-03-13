@HiltViewModel
class ProductionReportViewModel @Inject constructor(
    private val productionRepository: ProductionRepository,
    private val forecastingService: ForecastingService
) : ViewModel() {

    val productionMetrics: StateFlow<ProductionMetrics> = productionRepository
        .getProductionMetrics()
        .stateIn(viewModelScope, SharingStarted.Lazily, ProductionMetrics())

    val efficiency: StateFlow<ProductionEfficiency> = productionRepository
        .getProductionEfficiency()
        .stateIn(viewModelScope, SharingStarted.Lazily, ProductionEfficiency())

    fun generateForecast(days: Int) = viewModelScope.launch {
        val forecast = forecastingService.generateProductionForecast(days)
        // Handle forecast
    }
}
