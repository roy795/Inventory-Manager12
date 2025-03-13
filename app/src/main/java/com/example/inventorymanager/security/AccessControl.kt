@Singleton
class AccessControl @Inject constructor(
    private val authenticationService: AuthenticationService
) {
    fun checkPermission(permission: Permission): Boolean {
        val currentUser = authenticationService.currentUser.value ?: return false
        return when (currentUser.role) {
            UserRole.ADMIN -> true
            UserRole.MANAGER -> managerPermissions.contains(permission)
            UserRole.INVENTORY_CLERK -> inventoryClerkPermissions.contains(permission)
            UserRole.SALES_PERSON -> salesPersonPermissions.contains(permission)
            UserRole.PRODUCTION_WORKER -> productionWorkerPermissions.contains(permission)
        }
    }

    companion object {
        val managerPermissions = setOf(
            Permission.VIEW_REPORTS,
            Permission.MANAGE_INVENTORY,
            Permission.APPROVE_ORDERS,
            Permission.VIEW_ANALYTICS
        )

        val inventoryClerkPermissions = setOf(
            Permission.VIEW_INVENTORY,
            Permission.UPDATE_INVENTORY,
            Permission.CREATE_MATERIAL
        )

        val salesPersonPermissions = setOf(
            Permission.VIEW_INVENTORY,
            Permission.CREATE_MATERIAL,
            Permission.VIEW_REPORTS
        )

        val productionWorkerPermissions = setOf(
            Permission.VIEW_INVENTORY,
            Permission.UPDATE_INVENTORY
        )
    }
}

enum class Permission {
    VIEW_INVENTORY,
    UPDATE_INVENTORY,
    CREATE_MATERIAL,
    DELETE_MATERIAL,
    VIEW_REPORTS,
    MANAGE_INVENTORY,
    APPROVE_ORDERS,
    VIEW_ANALYTICS,
    MANAGE_USERS
} 