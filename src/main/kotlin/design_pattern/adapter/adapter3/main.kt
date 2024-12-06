package design_pattern.adapter.adapter3

fun main() {
    val audioPlayer = AudioPlayer()
    audioPlayer.playAudio("song.mp3")

    val videoPlayer = VideoPlayer()
    val adapter = VideoToAudioPlayer(videoPlayer)

    // Using the adapter to play a video file through the MediaPlayer interface
    adapter.playAudio("movie.mp4")
}