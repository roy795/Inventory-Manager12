@Singleton
class AnalyticsManager @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics
) {
    fun logScreenView(screenName: String) {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        }
    }

    fun logInventoryAction(action: String, itemId: String) {
        firebaseAnalytics.logEvent("inventory_action") {
            param("action", action)
            param("item_id", itemId)
        }
    }

    fun logExportAction(format: String) {
        firebaseAnalytics.logEvent("export_data") {
            param("format", format)
        }
    }

    fun logError(errorType: String, message: String) {
        firebaseAnalytics.logEvent("app_error") {
            param("error_type", errorType)
            param("error_message", message)
        }
    }
} 