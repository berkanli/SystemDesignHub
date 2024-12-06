package design_pattern.adapter.adapter3

// The Adapter class that adapts the VideoPlayer to be used as a MediaPlayer
class VideoToAudioPlayer(private val videoPlayer: VideoPlayer): MediaPlayer {
    override fun playAudio(fileName: String) {
        println("Using Adapter to play video as audio")
        videoPlayer.playVideo(fileName) // Delegate to VideoPlayer to play the video
    }
}