package news

import java.time.LocalDateTime

data class NewsResponse(
    val title: String,
    val station: Station,
    val description: String,
    val imageUrl: String,
    val publishedAt: LocalDateTime?,
) {
    companion object {
        @JvmStatic
        fun of(
            title: String,
            station: Station,
            description: String,
            imageUrl: String,
            publishedAt: LocalDateTime,
        ) = NewsResponse(
            title = title,
            station = station,
            description = description,
            imageUrl = imageUrl,
            publishedAt = publishedAt,
        )

        @JvmStatic
        fun empty(
            station: Station,
        ) = NewsResponse(
            title = "",
            station = station,
            description = "",
            imageUrl = "",
            publishedAt = null,
        )
    }
}