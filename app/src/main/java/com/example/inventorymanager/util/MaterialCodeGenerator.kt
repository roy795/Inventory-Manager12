class MaterialCodeGenerator @Inject constructor() {
    fun generateCode(materialId: Long, batchNumber: String): String {
        val timestamp = System.currentTimeMillis()
        return "MAT-${materialId}-${batchNumber}-${timestamp}"
    }
} 