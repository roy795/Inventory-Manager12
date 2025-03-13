@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun getUserByEmail(email: String): User? = userDao.getUserByEmail(email)
    
    suspend fun updateLastLogin(userId: String) = userDao.updateLastLogin(userId)
} 