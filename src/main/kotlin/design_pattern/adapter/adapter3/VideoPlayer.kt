package design_pattern.adapter.adapter3

// The VideoPlayer class that can only play video files
class VideoPlayer {
    fun playVideo(fileName: String){
        println("Playing video file: $fileName")
    }
}