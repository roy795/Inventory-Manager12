class BOQRepository @Inject constructor(
    private val boqDao: BOQDao
) {
    fun getAllBOQs() = boqDao.getAllBOQs()
    fun getBOQsByProject(projectName: String) = boqDao.getBOQsByProject(projectName)
    fun getProjectTotal(projectName: String) = boqDao.getProjectTotal(projectName)
    
    suspend fun addBOQItem(boq: BOQ) = boqDao.insert(boq)
    suspend fun updateBOQItem(boq: BOQ) = boqDao.update(boq)
    suspend fun deleteBOQItem(boq: BOQ) = boqDao.delete(boq)
} 