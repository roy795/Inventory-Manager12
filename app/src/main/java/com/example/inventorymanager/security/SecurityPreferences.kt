@Singleton
class SecurityPreferences @Inject constructor(
    private val context: Context
) {
    private val prefs = context.getSharedPreferences("security_prefs", Context.MODE_PRIVATE)

    fun saveAuthToken(token: String) {
        prefs.edit().putString("auth_token", token).apply()
    }

    fun getAuthToken(): String? {
        return prefs.getString("auth_token", null)
    }

    fun clearAuthToken() {
        prefs.edit().remove("auth_token").apply()
    }
} 