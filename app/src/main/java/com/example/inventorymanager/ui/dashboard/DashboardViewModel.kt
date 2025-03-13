@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository,
    private val comparisonService: ComparisonService
) : ViewModel() {

    val inventoryMetrics: StateFlow<MetricComparison> = dashboardRepository
        .getInventoryValuation()
        .map { current ->
            MetricComparison(
                current = current,
                previous = comparisonService.getPreviousInventoryValue()
            )
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, MetricComparison())

    val salesMetrics: StateFlow<MetricComparison> = dashboardRepository
        .getTotalSales()
        .map { current ->
            MetricComparison(
                current = current,
                previous = comparisonService.getPreviousSalesValue()
            )
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, MetricComparison())

    val productionMetrics: StateFlow<MetricComparison> = dashboardRepository
        .getProductionValue()
        .map { current ->
            MetricComparison(
                current = current,
                previous = comparisonService.getPreviousProductionValue()
            )
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, MetricComparison())
}

data class MetricComparison(
    val current: Double = 0.0,
    val previous: Double = 0.0
)
