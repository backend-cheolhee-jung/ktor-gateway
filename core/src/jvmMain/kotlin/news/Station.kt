package news

enum class Station(
    private val description: String,
) {
    BBC("British Broadcasting Corporation"),
    NEW_YORK_TIMES("The New York Times"),
}

object StationUrlManager {
    fun getUrl(station: Station) =
        when (station) {
            Station.BBC -> "https://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml"
            Station.NEW_YORK_TIMES -> "https://feeds.bbci.co.uk/news/rss.xml"
        }
}