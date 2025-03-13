class ComparisonChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var data: List<ChartDataPoint> = emptyList()
    private val chartRect = RectF()

    fun setData(newData: List<ChartDataPoint>) {
        data = newData
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (data.isEmpty()) return

        // Draw chart here
        updateChartRect()
        drawAxes(canvas)
        drawDataPoints(canvas)
    }

    private fun updateChartRect() {
        val padding = resources.getDimensionPixelSize(R.dimen.margin_medium)
        chartRect.set(
            padding.toFloat(),
            padding.toFloat(),
            width - padding.toFloat(),
            height - padding.toFloat()
        )
    }

    private fun drawAxes(canvas: Canvas) {
        paint.color = Color.GRAY
        paint.strokeWidth = 2f
        canvas.drawLine(
            chartRect.left,
            chartRect.bottom,
            chartRect.right,
            chartRect.bottom,
            paint
        )
        canvas.drawLine(
            chartRect.left,
            chartRect.top,
            chartRect.left,
            chartRect.bottom,
            paint
        )
    }

    private fun drawDataPoints(canvas: Canvas) {
        if (data.isEmpty()) return
        
        paint.color = Color.BLUE
        paint.strokeWidth = 4f
        
        val maxValue = data.maxOf { it.value }
        val minValue = data.minOf { it.value }
        val range = maxValue - minValue
        
        val xStep = chartRect.width() / (data.size - 1)
        
        val path = Path()
        path.moveTo(chartRect.left, getYPosition(data[0].value, minValue, range))
        
        for (i in 1 until data.size) {
            val x = chartRect.left + i * xStep
            val y = getYPosition(data[i].value, minValue, range)
            path.lineTo(x, y)
        }
        
        canvas.drawPath(path, paint)
    }

    private fun getYPosition(value: Float, minValue: Float, range: Float): Float {
        val percentage = (value - minValue) / range
        return chartRect.bottom - (chartRect.height() * percentage)
    }
}

data class ChartDataPoint(
    val label: String,
    val value: Float
)
