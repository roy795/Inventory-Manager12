@Entity(tableName = "production")
data class Production(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val productId: Long,
    val quantity: Double,
    val status: ProductionStatus,
    val startDate: Date,
    val completionDate: Date?,
    val bomId: Long
)

@Entity(tableName = "production_materials")
data class ProductionMaterial(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val productionId: Long,
    val materialCodeId: String,
    val plannedQuantity: Double,
    val actualQuantity: Double,
    val status: ConsumptionStatus
)
