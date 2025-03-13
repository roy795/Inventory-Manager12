class MetricComparisonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: ViewMetricComparisonBinding

    init {
        binding = ViewMetricComparisonBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData(title: String, current: Double, previous: Double) {
        binding.titleText.text = title
        binding.currentValue.text = "Current: ${formatValue(current)}"
        binding.previousValue.text = "Previous: ${formatValue(previous)}"
        
        val percentageChange = calculatePercentageChange(current, previous)
        binding.changePercentage.text = formatPercentage(percentageChange)
        binding.changePercentage.setTextColor(getColorForChange(percentageChange))
    }

    private fun formatValue(value: Double): String {
        return String.format("%.2f", value)
    }

    private fun calculatePercentageChange(current: Double, previous: Double): Double {
        if (previous == 0.0) return 0.0
        return ((current - previous) / previous) * 100
    }

    private fun formatPercentage(percentage: Double): String {
        val sign = if (percentage >= 0) "+" else ""
        return "$sign${String.format("%.1f", percentage)}%"
    }

    private fun getColorForChange(percentage: Double): Int {
        return when {
            percentage > 0 -> ContextCompat.getColor(context, android.R.color.holo_green_dark)
            percentage < 0 -> ContextCompat.getColor(context, android.R.color.holo_red_dark)
            else -> ContextCompat.getColor(context, android.R.color.darker_gray)
        }
    }
}
