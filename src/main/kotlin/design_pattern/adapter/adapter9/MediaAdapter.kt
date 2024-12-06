package design_pattern.adapter.adapter9

class MediaAdapter(private val advancedMediaPlayer: AdvancedMediaPlayer): MediaPlayer {
    override fun play(mediaType: String, fileName: String) {
        if (mediaType.equals("video", ignoreCase = true)){
            advancedMediaPlayer.playVideo(fileName)
        }
    }
}