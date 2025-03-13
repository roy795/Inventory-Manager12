package com.example.inventorymanager.ui.materials

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventorymanager.data.entity.Material
import com.example.inventorymanager.data.repository.MaterialRepository
import com.example.inventorymanager.security.SecurityInterceptor
import com.example.inventorymanager.validation.DataValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MaterialViewModel @Inject constructor(
    private val repository: MaterialRepository,
    private val securityInterceptor: SecurityInterceptor,
    private val dataValidator: DataValidator
) : ViewModel() {
    val allMaterials: Flow<List<Material>> = repository.getAllMaterials()
    val lowStockMaterials: Flow<List<Material>> = repository.getLowStockMaterials()

    fun addMaterial(name: String, quantity: Int, minQuantity: Int, unit: String, price: Double) {
        viewModelScope.launch {
            val material = Material(
                name = name,
                quantity = quantity,
                minQuantity = minQuantity,
                unit = unit,
                price = price,
                lastUpdated = Date()
            )

            val validationResult = dataValidator.validateMaterial(material)
            if (!validationResult.isValid) {
                // Handle validation errors
                return@launch
            }

            securityInterceptor.executeSecurely(
                permission = Permission.CREATE_MATERIAL,
                entityType = EntityType.MATERIAL,
                entityId = "new",
                action = TransactionAction.CREATE
            ) {
                repository.insertMaterial(material)
            }
        }
    }

    fun updateMaterial(material: Material) {
        viewModelScope.launch {
            val validationResult = dataValidator.validateMaterial(material)
            if (!validationResult.isValid) {
                return@launch
            }

            securityInterceptor.executeSecurely(
                permission = Permission.UPDATE_INVENTORY,
                entityType = EntityType.MATERIAL,
                entityId = material.id.toString(),
                action = TransactionAction.UPDATE
            ) {
                repository.updateMaterial(material)
            }
        }
    }

    fun deleteMaterial(material: Material) {
        viewModelScope.launch {
            securityInterceptor.executeSecurely(
                permission = Permission.DELETE_MATERIAL,
                entityType = EntityType.MATERIAL,
                entityId = material.id.toString(),
                action = TransactionAction.DELETE
            ) {
                repository.deleteMaterial(material)
            }
        }
    }
}
