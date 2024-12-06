package design_pattern.adapter.adapter11

fun main() {
    val localFileUploader = LocalFileUploader()
    val googleCloudStorage = GoogleCloudStorage()
    val cloudStorageAdapter = CloudStorageAdapter(googleCloudStorage)

    localFileUploader.uploadFile("document.txt")
    cloudStorageAdapter.uploadFile("image.png")
}