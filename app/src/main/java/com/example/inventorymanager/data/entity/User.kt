@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: String,  // Using string for flexibility with auth providers
    val email: String,
    val name: String,
    val role: UserRole,
    val status: UserStatus,
    val createdAt: Date = Date(),
    val lastLogin: Date? = null
)

enum class UserRole {
    ADMIN,
    MANAGER,
    INVENTORY_CLERK,
    SALES_PERSON,
    PRODUCTION_WORKER
}

enum class UserStatus {
    ACTIVE,
    INACTIVE,
    SUSPENDED
} 