@Singleton
class InventoryRepository @Inject constructor(
    private val materialDao: MaterialDao
) {
    fun getAllMaterials(): Flow<List<Material>> = materialDao.getAllMaterials()
    
    fun getLowStockMaterials(): Flow<List<Material>> = materialDao.getLowStockMaterials()
    
    suspend fun addMaterial(material: Material) = materialDao.insert(material)
    
    suspend fun updateMaterial(material: Material) = materialDao.update(material)
    
    suspend fun deleteMaterial(material: Material) = materialDao.delete(material)
    
    suspend fun getMaterialById(id: Long): Material? = materialDao.getMaterialById(id)
}
