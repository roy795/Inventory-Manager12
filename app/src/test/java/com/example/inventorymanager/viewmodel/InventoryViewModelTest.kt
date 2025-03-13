@RunWith(MockitoJUnitRunner::class)
class InventoryViewModelTest {
    @Mock
    private lateinit var repository: InventoryRepository
    
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    
    private lateinit var viewModel: InventoryViewModel

    @Before
    fun setup() {
        viewModel = InventoryViewModel(repository)
    }

    @Test
    fun `when search query changes, inventory is filtered`() = runTest {
        // Given
        val materials = listOf(
            Material(id = 1, name = "Steel"),
            Material(id = 2, name = "Wood")
        )
        whenever(repository.getAllMaterials())
            .thenReturn(flowOf(materials))

        // When
        viewModel.setSearchQuery("Steel")

        // Then
        val result = viewModel.inventory.first()
        assertTrue(result is UiState.Success)
        assertEquals(1, (result as UiState.Success).data.size)
    }
} 