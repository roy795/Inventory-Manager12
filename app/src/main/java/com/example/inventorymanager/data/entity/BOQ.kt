data class BOQ(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val projectName: String,
    val itemDescription: String,
    val quantity: Double,
    val unit: String,
    val unitPrice: Double,
    val totalAmount: Double,
    val createdDate: Date = Date(),
    val lastModified: Date = Date()
) 