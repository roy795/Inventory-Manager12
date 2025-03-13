@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val analyticsManager: AnalyticsManager
) : ViewModel() {

    private val _authState = MutableLiveData<Result<FirebaseUser>>()
    val authState: LiveData<Result<FirebaseUser>> = _authState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val result = authRepository.signIn(email, password)
                _authState.value = result
                if (result.isSuccess) {
                    analyticsManager.logScreenView("login_success")
                } else {
                    analyticsManager.logError("login_error", result.exceptionOrNull()?.message ?: "Unknown error")
                }
            } catch (e: Exception) {
                _authState.value = Result.failure(e)
                analyticsManager.logError("login_error", e.message ?: "Unknown error")
            }
        }
    }

    fun signup(email: String, password: String) {
        viewModelScope.launch {
            try {
                val result = authRepository.signUp(email, password)
                _authState.value = result
                if (result.isSuccess) {
                    analyticsManager.logScreenView("signup_success")
                } else {
                    analyticsManager.logError("signup_error", result.exceptionOrNull()?.message ?: "Unknown error")
                }
            } catch (e: Exception) {
                _authState.value = Result.failure(e)
                analyticsManager.logError("signup_error", e.message ?: "Unknown error")
            }
        }
    }
} 