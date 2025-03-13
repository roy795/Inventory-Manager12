@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
        observeViewModel()
    }

    private fun setupClickListeners() {
        binding.loginButton.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            viewModel.login(email, password)
        }

        binding.signupButton.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            viewModel.signup(email, password)
        }
    }

    private fun observeViewModel() {
        viewModel.authState.observe(this) { result ->
            when (result) {
                is Result.Success -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                is Result.Error -> {
                    Toast.makeText(this, result.exception.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
} 