package weather

enum class Weather(
    private val emoji: String,
) {
    SUNNY("☀️"),
    CLOUDY("☁️"),
    SHOWERS("🌦️"),
    PARTLY_CLOUDY("🌤️"),
    OVERCAST("🌥️"),
    RAINY("🌧️"),
    SNOWY("❄️"),
    THUNDERSTORM("⛈️"),
    WINDY("💨"),
    FOGGY("🌫️"),
    UNKNOWN("❓");

    companion object {
        fun fromEmoji(emoji: String) =
            entries.firstOrNull { it.emoji == emoji } ?: UNKNOWN
    }
}