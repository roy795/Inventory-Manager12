@Entity(tableName = "transaction_logs")
data class TransactionLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val action: String,
    val details: String,
    val timestamp: Date = Date()
) 