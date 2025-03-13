enum class UserRole {
    ADMIN,
    MANAGER,
    STAFF;

    fun canExport() = this in listOf(ADMIN, MANAGER)
    fun canDelete() = this == ADMIN
    fun canEditPrices() = this in listOf(ADMIN, MANAGER)
} 