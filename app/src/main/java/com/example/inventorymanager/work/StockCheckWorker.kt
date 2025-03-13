@HiltWorker
class StockCheckWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val inventoryRepository: InventoryRepository,
    private val notificationManager: NotificationManager
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val lowStockItems = inventoryRepository.getLowStockMaterials().first()
            lowStockItems.forEach { material ->
                notificationManager.showLowStockNotification(material)
            }
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    companion object {
        fun schedulePeriodicCheck(workManager: WorkManager) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val request = PeriodicWorkRequestBuilder<StockCheckWorker>(
                repeatInterval = 24,
                repeatIntervalTimeUnit = TimeUnit.HOURS
            )
                .setConstraints(constraints)
                .build()

            workManager.enqueueUniquePeriodicWork(
                "stock_check",
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
        }
    }
} 