@HiltAndroidTest
class InventoryFragmentTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun displaysMaterialsList() {
        launchFragmentInHiltContainer<InventoryFragment> {
            onView(withId(R.id.inventoryRecyclerView))
                .check(matches(isDisplayed()))
        }
    }
} 