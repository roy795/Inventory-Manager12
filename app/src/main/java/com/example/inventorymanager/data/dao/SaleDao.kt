@Dao
interface SaleDao {
    @Query("SELECT * FROM sales ORDER BY date DESC")
    fun getAllSales(): Flow<List<Sale>>

    @Query("SELECT * FROM sales WHERE id = :id")
    suspend fun getSaleById(id: Long): Sale?

    @Query("SELECT SUM(totalAmount) FROM sales WHERE status = 'COMPLETED'")
    fun getTotalSales(): Flow<Double>

    @Insert
    suspend fun insert(sale: Sale): Long

    @Update
    suspend fun update(sale: Sale)

    @Delete
    suspend fun delete(sale: Sale)

    @Query("SELECT * FROM sales WHERE date BETWEEN :startDate AND :endDate")
    fun getSalesInDateRange(startDate: Date, endDate: Date): Flow<List<Sale>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSale(sale: Sale): Long

    @Insert
    suspend fun insertSaleItems(items: List<SaleItem>)

    @Transaction
    suspend fun insertSaleWithItems(sale: Sale, items: List<SaleItem>) {
        val saleId = insertSale(sale)
        insertSaleItems(items.map { it.copy(saleId = saleId) })
    }
}
