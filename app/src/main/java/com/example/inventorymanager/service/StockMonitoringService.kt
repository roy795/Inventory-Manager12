@AndroidEntryPoint
class StockMonitoringService : Service() {
    @Inject
    lateinit var inventoryRepository: InventoryRepository
    
    @Inject
    lateinit var notificationManager: NotificationManager

    private val serviceScope = CoroutineScope(Dispatchers.Default + Job())

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        monitorStock()
        return START_STICKY
    }

    private fun monitorStock() {
        serviceScope.launch {
            inventoryRepository.getLowStockMaterials()
                .collect { materials ->
                    materials.forEach { material ->
                        notificationManager.showLowStockNotification(material)
                    }
                }
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }
} 