@Dao
interface BOQDao {
    @Query("SELECT * FROM boq ORDER BY projectName, itemDescription")
    fun getAllBOQs(): Flow<List<BOQ>>

    @Query("SELECT * FROM boq WHERE projectName = :projectName")
    fun getBOQsByProject(projectName: String): Flow<List<BOQ>>

    @Insert
    suspend fun insert(boq: BOQ): Long

    @Update
    suspend fun update(boq: BOQ)

    @Delete
    suspend fun delete(boq: BOQ)

    @Query("SELECT SUM(totalAmount) FROM boq WHERE projectName = :projectName")
    fun getProjectTotal(projectName: String): Flow<Double>
} 