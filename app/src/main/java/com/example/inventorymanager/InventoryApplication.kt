@HiltAndroidApplication
class InventoryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)
    }
} 