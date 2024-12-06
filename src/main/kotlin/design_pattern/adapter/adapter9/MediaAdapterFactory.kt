package design_pattern.adapter.adapter9


class MediaAdapterFactory {
    fun getAdapter(mediaType: String): MediaPlayer? {
        return when(mediaType.lowercase()){
            "video" -> MediaAdapter(VideoPlayer())
            else -> null // No adapter needed for audio
        }
    }
}