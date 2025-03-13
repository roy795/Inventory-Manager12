@Singleton
class SalesRepository @Inject constructor(
    private val saleDao: SaleDao,
    private val materialDao: MaterialDao
) {
    fun getAllSales(): Flow<List<Sale>> = saleDao.getAllSales()
    
    fun getTotalSales(): Flow<Double> = saleDao.getTotalSales()
    
    suspend fun createSale(sale: Sale, items: List<SaleItem>) {
        saleDao.insertSaleWithItems(sale, items)
        // Update material quantities
        items.forEach { item ->
            materialDao.getMaterialById(item.materialId)?.let { material ->
                materialDao.update(material.copy(
                    quantity = material.quantity - item.quantity
                ))
            }
        }
    }
} 