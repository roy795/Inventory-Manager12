class LoginViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: LoginViewModel
    private lateinit var authRepository: AuthRepository
    private lateinit var analyticsManager: AnalyticsManager

    @Before
    fun setup() {
        authRepository = mock()
        analyticsManager = mock()
        viewModel = LoginViewModel(authRepository, analyticsManager)
    }

    @Test
    fun `login success should update auth state`() = runTest {
        val user = mock<FirebaseUser>()
        whenever(authRepository.signIn(any(), any())).thenReturn(Result.success(user))

        viewModel.login("test@test.com", "password")

        verify(authRepository).signIn("test@test.com", "password")
        verify(analyticsManager).logScreenView("login_success")
        assert(viewModel.authState.value?.isSuccess == true)
    }
} 