@Singleton
class NotificationManager @Inject constructor(
    private val context: Context
) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) 
        as android.app.NotificationManager

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Inventory Alerts",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notifications for inventory status"
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showLowStockNotification(material: Material) {
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_inventory_warning)
            .setContentTitle("Low Stock Alert")
            .setContentText("${material.name} is running low (${material.quantity} remaining)")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(material.id.toInt(), notification)
    }

    companion object {
        private const val CHANNEL_ID = "inventory_alerts"
    }
} 