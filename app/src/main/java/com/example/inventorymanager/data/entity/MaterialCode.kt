@Entity(tableName = "material_codes")
data class MaterialCode(
    @PrimaryKey
    val code: String,
    val materialId: Long,
    val batchNumber: String,
    val quantity: Double,
    val dateCreated: Date = Date()
) 