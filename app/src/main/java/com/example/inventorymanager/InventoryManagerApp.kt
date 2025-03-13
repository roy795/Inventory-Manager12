@HiltAndroidApp
class InventoryManagerApp : Application() {
    @Inject
    lateinit var workManager: WorkManager

    override fun onCreate() {
        super.onCreate()
        setupWorkManager()
    }

    private fun setupWorkManager() {
        StockCheckWorker.schedulePeriodicCheck(workManager)
    }
} 