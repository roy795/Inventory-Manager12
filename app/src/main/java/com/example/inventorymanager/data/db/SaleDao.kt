@Dao
interface SaleDao {
    @Transaction
    @Query("SELECT * FROM sales ORDER BY date DESC")
    fun getAllSales(): Flow<List<Sale>>

    @Insert
    suspend fun insertSale(sale: Sale): Long

    @Insert
    suspend fun insertSaleItems(items: List<SaleItem>)

    @Transaction
    suspend fun insertSaleWithItems(sale: Sale, items: List<SaleItem>) {
        val saleId = insertSale(sale)
        insertSaleItems(items.map { it.copy(saleId = saleId) })
    }

    @Query("SELECT SUM(totalAmount) FROM sales")
    fun getTotalSales(): Flow<Double>
} 