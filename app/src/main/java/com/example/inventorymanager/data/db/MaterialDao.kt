@Dao
interface MaterialDao {
    @Query("SELECT * FROM materials ORDER BY name ASC")
    fun getAllMaterials(): Flow<List<Material>>

    @Query("SELECT * FROM materials WHERE quantity <= minQuantity")
    fun getLowStockMaterials(): Flow<List<Material>>

    @Insert
    suspend fun insert(material: Material): Long

    @Update
    suspend fun update(material: Material)

    @Delete
    suspend fun delete(material: Material)

    @Query("SELECT * FROM materials WHERE id = :id")
    suspend fun getMaterialById(id: Long): Material?
} 