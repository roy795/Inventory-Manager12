@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Query("UPDATE users SET lastLogin = :lastLogin WHERE id = :userId")
    suspend fun updateLastLogin(userId: String, lastLogin: Date = Date())
} 