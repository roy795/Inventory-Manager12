import androidx.room.TypeConverter
import java.util.Date
import com.example.inventorymanager.data.entity.SaleStatus
import com.example.inventorymanager.data.entity.UserRole
import com.example.inventorymanager.data.entity.UserStatus
import com.example.inventorymanager.data.entity.MovementType

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromUserRole(role: UserRole): String {
        return role.name
    }

    @TypeConverter
    fun toUserRole(role: String): UserRole {
        return UserRole.valueOf(role)
    }

    @TypeConverter
    fun fromUserStatus(status: UserStatus): String {
        return status.name
    }

    @TypeConverter
    fun toUserStatus(status: String): UserStatus {
        return UserStatus.valueOf(status)
    }

    @TypeConverter
    fun fromSaleStatus(status: SaleStatus): String {
        return status.name
    }

    @TypeConverter
    fun toSaleStatus(status: String): SaleStatus {
        return SaleStatus.valueOf(status)
    }

    @TypeConverter
    fun fromMovementType(type: MovementType): String {
        return type.name
    }

    @TypeConverter
    fun toMovementType(type: String): MovementType {
        return MovementType.valueOf(type)
    }
} 