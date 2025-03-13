@Dao
interface TransactionLogDao {
    @Insert
    suspend fun insert(log: TransactionLog)

    @Query("SELECT * FROM transaction_logs ORDER BY timestamp DESC")
    fun getAllLogs(): Flow<List<TransactionLog>>

    @Query("SELECT * FROM transaction_logs WHERE userId = :userId")
    fun getLogsByUser(userId: String): Flow<List<TransactionLog>>
} 