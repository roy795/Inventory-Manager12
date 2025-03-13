class TransactionService @Inject constructor(
    private val stockMovementDao: StockMovementDao,
    private val materialCodeDao: MaterialCodeDao
) {
    suspend fun recordMovement(
        materialCode: String,
        type: MovementType,
        quantity: Double,
        reference: String,
        notes: String? = null
    ) {
        val movement = StockMovement(
            materialCode = materialCode,
            type = type,
            quantity = quantity,
            reference = reference,
            notes = notes
        )
        stockMovementDao.insert(movement)
        updateMaterialCodeQuantity(materialCode, quantity, type)
    }

    private suspend fun updateMaterialCodeQuantity(
        code: String,
        quantity: Double,
        type: MovementType
    ) {
        val adjustment = when (type) {
            MovementType.RECEIPT, MovementType.RETURN -> quantity
            MovementType.PRODUCTION_CONSUMPTION, MovementType.SALE -> -quantity
            MovementType.ADJUSTMENT -> quantity
        }
        materialCodeDao.adjustQuantity(code, adjustment)
    }
} 