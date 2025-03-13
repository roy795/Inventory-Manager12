@Singleton
class SecurityInterceptor @Inject constructor(
    private val accessControl: AccessControl,
    private val transactionLogger: TransactionLogger
) {
    suspend fun <T> executeSecurely(
        permission: Permission,
        entityType: EntityType,
        entityId: String,
        action: TransactionAction,
        operation: suspend () -> T
    ): Result<T> {
        return try {
            if (!accessControl.checkPermission(permission)) {
                return Result.failure(SecurityException("Permission denied"))
            }

            val result = operation()
            
            transactionLogger.logTransaction(
                action = action,
                entityType = entityType,
                entityId = entityId
            )

            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 