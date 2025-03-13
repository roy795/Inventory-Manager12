@Entity(tableName = "sales")
data class Sale(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val orderNumber: String,
    val customerName: String,
    val totalAmount: Double,
    val date: Long
)

@Entity(
    tableName = "sale_items",
    foreignKeys = [
        ForeignKey(
            entity = Sale::class,
            parentColumns = ["id"],
            childColumns = ["saleId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Material::class,
            parentColumns = ["id"],
            childColumns = ["materialId"],
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class SaleItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val saleId: Long,
    val materialId: Long,
    val quantity: Double,
    val unitPrice: Double
) 