@Singleton
class TransactionLogger @Inject constructor(
    private val logDao: TransactionLogDao,
    private val authenticationService: AuthenticationService
) {
    suspend fun logTransaction(
        action: TransactionAction,
        entityType: EntityType,
        entityId: String,
        details: String? = null
    ) {
        val currentUser = authenticationService.currentUser.value
        val log = TransactionLog(
            userId = currentUser?.id,
            action = action,
            entityType = entityType,
            entityId = entityId,
            details = details,
            timestamp = Date(),
            ipAddress = getDeviceIp()
        )
        logDao.insert(log)
    }

    private fun getDeviceIp(): String {
        // Implement device IP retrieval
        return "127.0.0.1"
    }
}

@Entity(tableName = "transaction_logs")
data class TransactionLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String?,
    val action: TransactionAction,
    val entityType: EntityType,
    val entityId: String,
    val details: String?,
    val timestamp: Date,
    val ipAddress: String
)

enum class TransactionAction {
    CREATE,
    READ,
    UPDATE,
    DELETE,
    LOGIN,
    LOGOUT,
    APPROVE,
    REJECT
}

enum class EntityType {
    USER,
    MATERIAL,
    SALE,
    PRODUCTION,
    INVENTORY,
    BOQ
} 