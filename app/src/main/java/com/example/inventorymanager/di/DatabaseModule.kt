@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideMaterialDao(database: AppDatabase) = database.materialDao()

    @Provides
    fun provideSaleDao(database: AppDatabase) = database.saleDao()

    @Provides
    fun provideUserDao(database: AppDatabase) = database.userDao()

    @Provides
    fun provideBOQDao(database: AppDatabase) = database.boqDao()

    @Provides
    fun provideProductionDao(database: AppDatabase) = database.productionDao()

    @Provides
    fun provideTransactionLogDao(database: AppDatabase) = database.transactionLogDao()
} 