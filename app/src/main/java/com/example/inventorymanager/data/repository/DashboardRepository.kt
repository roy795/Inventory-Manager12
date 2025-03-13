class DashboardRepository @Inject constructor(
    private val materialDao: MaterialDao,
    private val productionDao: ProductionDao,
    private val stockMovementDao: StockMovementDao
) {
    fun getLowStockMaterials(threshold: Double): Flow<List<Material>> =
        materialDao.getMaterialsBelowThreshold(threshold)

    fun getInventoryValuation(): Flow<Double> =
        materialDao.getTotalInventoryValue()

    fun getStockMovements(): Flow<List<StockMovement>> =
        stockMovementDao.getRecentMovements()

    fun getWorkInProgress(): Flow<List<Production>> =
        productionDao.getActiveProductions()
} 