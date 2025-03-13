@Dao
interface MaterialDao {
    @Query("SELECT * FROM materials")
    fun getAllMaterials(): Flow<List<Material>>

    @Query("SELECT * FROM materials WHERE quantity <= minQuantity")
    fun getLowStockMaterials(): Flow<List<Material>>

    @Query("SELECT * FROM materials WHERE id = :id")
    suspend fun getMaterialById(id: Long): Material?

    @Query("SELECT SUM(quantity * price) FROM materials")
    fun getTotalInventoryValue(): Flow<Double>

    @Query("SELECT * FROM materials WHERE quantity < minQuantity")
    fun getMaterialsBelowThreshold(threshold: Double): Flow<List<Material>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMaterial(material: Material)

    @Update
    suspend fun updateMaterial(material: Material)

    @Delete
    suspend fun deleteMaterial(material: Material)
}
