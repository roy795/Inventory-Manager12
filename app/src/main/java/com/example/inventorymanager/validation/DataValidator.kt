class DataValidator @Inject constructor() {
    fun validateMaterial(material: Material): ValidationResult {
        val errors = mutableListOf<ValidationError>()
        
        if (material.name.isBlank()) {
            errors.add(ValidationError("name", "Name cannot be empty"))
        }
        
        if (material.quantity < 0) {
            errors.add(ValidationError("quantity", "Quantity cannot be negative"))
        }
        
        if (material.price < 0) {
            errors.add(ValidationError("price", "Price cannot be negative"))
        }
        
        return ValidationResult(errors.isEmpty(), errors)
    }

    fun validateSale(sale: Sale): ValidationResult {
        val errors = mutableListOf<ValidationError>()
        
        if (sale.orderNumber.isBlank()) {
            errors.add(ValidationError("orderNumber", "Order number cannot be empty"))
        }
        
        if (sale.totalAmount <= 0) {
            errors.add(ValidationError("totalAmount", "Total amount must be greater than zero"))
        }
        
        return ValidationResult(errors.isEmpty(), errors)
    }
}

data class ValidationResult(
    val isValid: Boolean,
    val errors: List<ValidationError> = emptyList()
)

data class ValidationError(
    val field: String,
    val message: String
) 