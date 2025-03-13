@Entity(tableName = "stock_movements")
data class StockMovement(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val materialCode: String,
    val type: MovementType,
    val quantity: Double,
    val date: Date = Date(),
    val reference: String, // PO/SO/Production number
    val notes: String?
)

enum class MovementType {
    RECEIPT, PRODUCTION_CONSUMPTION, SALE, RETURN, ADJUSTMENT
} 