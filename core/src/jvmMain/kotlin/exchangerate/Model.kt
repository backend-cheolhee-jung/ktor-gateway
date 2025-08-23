package exchangerate

import java.math.BigDecimal
import java.time.LocalDateTime

data class ExchangeRateResponse(
    val to: Currency,
    val from: Currency,
    val rate: BigDecimal,
    val datetime: LocalDateTime,
) {
    companion object {
        @JvmStatic
        fun of(
            from: Currency,
            to: Currency,
            rate: BigDecimal,
        ) =
            ExchangeRateResponse(
                from = from,
                to = to,
                rate = rate,
                datetime = LocalDateTime.now(),
            )
    }
}