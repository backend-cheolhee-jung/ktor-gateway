package exchangerate

enum class Currency(
    private val description: String,
) {
    KRW("대한민국 원"),
    USD("미국 달러"),
    JPY("일본 엔"),
    EUR("유로"),
}