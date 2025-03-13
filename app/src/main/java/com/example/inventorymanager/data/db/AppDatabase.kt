import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.inventorymanager.data.Converters
import com.example.inventorymanager.data.dao.*
import com.example.inventorymanager.data.entity.*

@Database(
    entities = [
        Material::class, 
        Sale::class, 
        SaleItem::class,
        User::class,
        BOQ::class,
        BillOfQuantities::class,
        BOQMaterial::class,
        MaterialCode::class,
        Production::class,
        ProductionMaterial::class,
        StockMovement::class,
        TransactionLog::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun materialDao(): MaterialDao
    abstract fun saleDao(): SaleDao
    abstract fun userDao(): UserDao
    abstract fun boqDao(): BOQDao
    abstract fun productionDao(): ProductionDao
    abstract fun transactionLogDao(): TransactionLogDao

    companion object {
        const val DATABASE_NAME = "inventory_manager.db"
    }
} 