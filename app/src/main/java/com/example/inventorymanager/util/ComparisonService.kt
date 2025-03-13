@Singleton
class ComparisonService @Inject constructor(
    private val inventoryRepository: InventoryRepository,
    private val salesRepository: SalesRepository,
    private val productionRepository: ProductionRepository
) {
    suspend fun getPreviousInventoryValue(): Double {
        return inventoryRepository.getPreviousPeriodValue()
    }

    suspend fun getPreviousSalesValue(): Double {
        return salesRepository.getPreviousPeriodValue()
    }

    suspend fun getPreviousProductionValue(): Double {
        return productionRepository.getPreviousPeriodValue()
    }

    fun calculateGrowthRate(current: Double, previous: Double): Double {
        if (previous == 0.0) return 0.0
        return ((current - previous) / previous) * 100
    }

    fun calculateTrend(values: List<Double>): TrendDirection {
        if (values.size < 2) return TrendDirection.STABLE
        val firstHalf = values.take(values.size / 2).average()
        val secondHalf = values.takeLast(values.size / 2).average()
        return when {
            secondHalf > firstHalf -> TrendDirection.UP
            secondHalf < firstHalf -> TrendDirection.DOWN
            else -> TrendDirection.STABLE
        }
    }
}

enum class TrendDirection {
    UP, DOWN, STABLE
}
