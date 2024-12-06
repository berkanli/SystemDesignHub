package design_pattern.adapter.adapter9

class UniversalMediaPlayer : MediaPlayer {
    private val audioPlayer = AudioPlayer()
    private var mediaAdapterFactory = MediaAdapterFactory()

    override fun play(mediaType: String, fileName: String) {
        when (mediaType.lowercase()) {
            "audio" -> audioPlayer.playAudio(fileName)
            else -> {
                val adapter = mediaAdapterFactory.getAdapter(mediaType)
                adapter?.play(mediaType, fileName) ?: println("Invalid media type ${mediaType}")
            }
        }
    }
}