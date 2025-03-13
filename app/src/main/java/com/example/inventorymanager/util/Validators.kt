object Validators {
    fun validateMaterial(material: Material): ValidationResult {
        return when {
            material.name.isBlank() -> 
                ValidationResult.Error("Material name cannot be empty")
            material.quantity < 0 -> 
                ValidationResult.Error("Quantity cannot be negative")
            material.price < 0 -> 
                ValidationResult.Error("Price cannot be negative")
            material.minQuantity < 0 -> 
                ValidationResult.Error("Minimum quantity cannot be negative")
            else -> ValidationResult.Success
        }
    }

    fun validateEmail(email: String): ValidationResult {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return when {
            email.isBlank() -> ValidationResult.Error("Email cannot be empty")
            !email.matches(emailPattern.toRegex()) -> 
                ValidationResult.Error("Invalid email format")
            else -> ValidationResult.Success
        }
    }

    fun validatePassword(password: String): ValidationResult {
        return when {
            password.length < 8 -> 
                ValidationResult.Error("Password must be at least 8 characters")
            !password.contains(Regex("[A-Z]")) -> 
                ValidationResult.Error("Password must contain at least one uppercase letter")
            !password.contains(Regex("[0-9]")) -> 
                ValidationResult.Error("Password must contain at least one number")
            else -> ValidationResult.Success
        }
    }
}

sealed class ValidationResult {
    object Success : ValidationResult()
    data class Error(val message: String) : ValidationResult()
} 