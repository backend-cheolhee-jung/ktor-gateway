package weather

data class WeatherResponse(
    val city: City,
    val weather: Weather,
    val temperature: Double,
) {
    companion object {
        @JvmStatic
        fun of(
            city: City,
            weather: Weather,
            temperature: Double
        ) = WeatherResponse(city, weather, temperature)
    }
}