package design_pattern.adapter.adapter11

class CloudStorageAdapter(private val googleCloudStorage: GoogleCloudStorage): LegacyFileUploader {
    override fun uploadFile(fileName: String) {
        googleCloudStorage.uploadToCloud(fileName)
    }

}