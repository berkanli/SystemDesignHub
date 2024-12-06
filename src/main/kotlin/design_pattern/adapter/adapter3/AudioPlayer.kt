package design_pattern.adapter.adapter3

// A concrete implementation of MediaPlayer interface
class AudioPlayer: MediaPlayer {
    override fun playAudio(fileName: String) {
        println("Playing audio file: $fileName")
    }
}