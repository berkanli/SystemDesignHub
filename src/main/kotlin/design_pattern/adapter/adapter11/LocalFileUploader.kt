package design_pattern.adapter.adapter11

class LocalFileUploader: LegacyFileUploader {
    override fun uploadFile(fileName: String) {
        println("Uploading $fileName to local file system...")
    }

}