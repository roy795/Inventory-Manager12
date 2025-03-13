data class ProductionSummary(
    val totalProductions: Int,
    val completedProductions: Int,
    val inProgressProductions: Int,
    val efficiency: Double,
    val averageCompletionTime: Long
)

data class ProductionMetrics(
    val dailyOutput: Double,
    val weeklyOutput: Double,
    val monthlyOutput: Double,
    val yearlyOutput: Double,
    val outputTrend: List<ChartDataPoint>
)

data class ProductionEfficiency(
    val targetOutput: Double,
    val actualOutput: Double,
    val efficiency: Double,
    val bottlenecks: List<String>
)
