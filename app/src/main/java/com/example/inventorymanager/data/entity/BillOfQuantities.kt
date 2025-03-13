@Entity(tableName = "bill_of_quantities")
data class BillOfQuantities(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val productId: Long,
    val version: Int,
    val isActive: Boolean,
    val wasteFactor: Double,
    val lastUpdated: Date = Date()
)

@Entity(tableName = "boq_materials")
data class BOQMaterial(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val boqId: Long,
    val materialId: Long,
    val quantity: Double,
    val wasteFactor: Double
) 