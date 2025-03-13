package com.example.inventorymanager.data.repository

import com.example.inventorymanager.data.dao.MaterialDao
import com.example.inventorymanager.data.entity.Material
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MaterialRepository @Inject constructor(
    private val materialDao: MaterialDao
) {
    fun getAllMaterials(): Flow<List<Material>> = materialDao.getAllMaterials()
    
    fun getLowStockMaterials(): Flow<List<Material>> = materialDao.getLowStockMaterials()
    
    suspend fun getMaterialById(id: Long): Material? = materialDao.getMaterialById(id)
    
    suspend fun insertMaterial(material: Material): Long = materialDao.insert(material)
    
    suspend fun updateMaterial(material: Material) = materialDao.update(material)
    
    suspend fun deleteMaterial(material: Material) = materialDao.delete(material)
}
