@Entity(tableName = "materials")
data class Material(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val quantity: Double,
    val minQuantity: Double,
    val unit: String,
    val price: Double,
    val lastUpdated: Date = Date()
)
