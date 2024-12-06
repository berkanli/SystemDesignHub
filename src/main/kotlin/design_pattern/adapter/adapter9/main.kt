package design_pattern.adapter.adapter9

fun main() {
    val player = UniversalMediaPlayer()

    player.play("audio", "song.mp3")   // Plays audio file using AudioPlayer
    player.play("video", "movie.mp4")  // Plays video file using MediaAdapter and VideoPlayer
    player.play("txt", "document.txt") // Invalid media type
}