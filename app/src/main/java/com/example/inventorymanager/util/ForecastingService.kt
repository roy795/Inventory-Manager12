@Singleton
class ForecastingService @Inject constructor(
    private val inventoryRepository: InventoryRepository,
    private val salesRepository: SalesRepository,
    private val productionRepository: ProductionRepository
) {
    suspend fun generateInventoryForecast(days: Int): List<ForecastPoint> {
        val historicalData = inventoryRepository.getHistoricalData()
        return calculateForecast(historicalData, days)
    }

    suspend fun generateSalesForecast(days: Int): List<ForecastPoint> {
        val historicalData = salesRepository.getHistoricalData()
        return calculateForecast(historicalData, days)
    }

    suspend fun generateProductionForecast(days: Int): List<ForecastPoint> {
        val historicalData = productionRepository.getHistoricalData()
        return calculateForecast(historicalData, days)
    }

    private fun calculateForecast(
        historicalData: List<DataPoint>,
        daysToForecast: Int
    ): List<ForecastPoint> {
        // Simple moving average implementation
        val movingAverageWindow = 7
        val averages = historicalData
            .windowed(movingAverageWindow)
            .map { window ->
                window.map { it.value }.average()
            }

        return (1..daysToForecast).map { day ->
            ForecastPoint(
                date = Date(System.currentTimeMillis() + (day * 24 * 60 * 60 * 1000)),
                value = averages.last()
            )
        }
    }
}

data class DataPoint(
    val date: Date,
    val value: Double
)

data class ForecastPoint(
    val date: Date,
    val value: Double,
    val confidenceInterval: Double = 0.95
)
