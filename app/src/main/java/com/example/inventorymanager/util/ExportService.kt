@Singleton
class ExportService @Inject constructor(
    private val context: Context
) {
    suspend fun exportToCSV(data: List<Any>, fileName: String): Uri {
        return withContext(Dispatchers.IO) {
            val file = File(context.cacheDir, fileName)
            file.writer().use { writer ->
                // Write headers
                writer.write(getHeaders(data.firstOrNull()))
                writer.write("\n")

                // Write data
                data.forEach { item ->
                    writer.write(convertToCSV(item))
                    writer.write("\n")
                }
            }
            FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                file
            )
        }
    }

    private fun getHeaders(item: Any?): String {
        return item?.javaClass?.declaredFields
            ?.joinToString(",") { it.name } ?: ""
    }

    private fun convertToCSV(item: Any): String {
        return item.javaClass.declaredFields
            .map { field ->
                field.isAccessible = true
                field.get(item)?.toString() ?: ""
            }
            .joinToString(",")
    }
}
