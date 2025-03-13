@Singleton
class BackupManager @Inject constructor(
    private val context: Context,
    private val database: AppDatabase,
    private val firestore: FirebaseFirestore
) {
    suspend fun backupToCloud() = withContext(Dispatchers.IO) {
        try {
            val materials = database.materialDao().getAllMaterialsSync()
            firestore.collection("backups")
                .document(getCurrentUserId())
                .set(mapOf("materials" to materials))
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun restoreFromCloud() = withContext(Dispatchers.IO) {
        try {
            val snapshot = firestore.collection("backups")
                .document(getCurrentUserId())
                .get()
                .await()
            
            val materials = snapshot.get("materials") as? List<Material>
            materials?.let { database.materialDao().insertAll(it) }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 