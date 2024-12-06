package design_pattern.adapter.adapter9

class VideoPlayer : AdvancedMediaPlayer {
    override fun playVideo(fileName: String) {
        println("Playing video file: $fileName")
    }
}