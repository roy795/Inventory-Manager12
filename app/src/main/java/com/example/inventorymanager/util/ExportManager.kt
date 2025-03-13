@Singleton
class ExportManager @Inject constructor(
    private val context: Context,
    private val inventoryRepository: InventoryRepository
) {
    suspend fun exportInventoryToCSV(): Result<Uri> = withContext(Dispatchers.IO) {
        try {
            val materials = inventoryRepository.getAllMaterials().first()
            val file = File(context.cacheDir, "inventory_${System.currentTimeMillis()}.csv")
            
            file.writer().use { writer ->
                // Write headers
                writer.write("Name,Quantity,Unit,Price,Last Updated\n")
                
                // Write data
                materials.forEach { material ->
                    writer.write("${material.name},${material.quantity},${material.unit},${material.price},${material.lastUpdated}\n")
                }
            }

            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )
            Result.success(uri)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun exportInventoryToPDF(): Result<Uri> = withContext(Dispatchers.IO) {
        try {
            val materials = inventoryRepository.getAllMaterials().first()
            val file = File(context.cacheDir, "inventory_${System.currentTimeMillis()}.pdf")
            
            PdfDocument().apply {
                val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
                val page = startPage(pageInfo)
                
                val canvas = page.canvas
                val paint = Paint()
                var y = 50f
                
                // Draw title
                paint.textSize = 20f
                canvas.drawText("Inventory Report", 50f, y, paint)
                y += 40f
                
                // Draw headers
                paint.textSize = 14f
                canvas.drawText("Name", 50f, y, paint)
                canvas.drawText("Quantity", 200f, y, paint)
                canvas.drawText("Price", 300f, y, paint)
                y += 20f
                
                // Draw data
                materials.forEach { material ->
                    canvas.drawText(material.name, 50f, y, paint)
                    canvas.drawText(material.quantity.toString(), 200f, y, paint)
                    canvas.drawText("$${material.price}", 300f, y, paint)
                    y += 20f
                }
                
                finishPage(page)
                writeTo(FileOutputStream(file))
                close()
            }

            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )
            Result.success(uri)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 