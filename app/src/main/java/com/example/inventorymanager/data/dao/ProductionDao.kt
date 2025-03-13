@Dao
interface ProductionDao {
    @Query("SELECT * FROM production ORDER BY startDate DESC")
    fun getAllProductions(): Flow<List<Production>>

    @Query("SELECT * FROM production WHERE status = 'IN_PROGRESS'")
    fun getActiveProductions(): Flow<List<Production>>

    @Query("SELECT * FROM production WHERE id = :id")
    suspend fun getProductionById(id: Long): Production?

    @Insert
    suspend fun insert(production: Production): Long

    @Update
    suspend fun update(production: Production)

    @Delete
    suspend fun delete(production: Production)

    @Query("SELECT * FROM production_materials WHERE productionId = :productionId")
    fun getProductionMaterials(productionId: Long): Flow<List<ProductionMaterial>>

    @Insert
    suspend fun insertProductionMaterial(productionMaterial: ProductionMaterial): Long

    @Update
    suspend fun updateProductionMaterial(productionMaterial: ProductionMaterial)
}
