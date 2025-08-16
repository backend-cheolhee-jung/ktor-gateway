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
    HAZY("🌫️");

    companion object {
        fun fromEmoji(emoji: String) =
            entries.firstOrNull { it.emoji == emoji } ?: SUNNY
    }
}