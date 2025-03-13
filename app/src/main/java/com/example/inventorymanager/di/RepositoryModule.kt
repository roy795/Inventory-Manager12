@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideInventoryRepository(
        materialDao: MaterialDao
    ): InventoryRepository {
        return InventoryRepository(materialDao)
    }

    @Provides
    @Singleton
    fun provideSalesRepository(
        saleDao: SaleDao,
        materialDao: MaterialDao
    ): SalesRepository {
        return SalesRepository(saleDao, materialDao)
    }
} 