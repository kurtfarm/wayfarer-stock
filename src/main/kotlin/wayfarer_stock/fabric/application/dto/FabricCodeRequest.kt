package wayfarer_stock.fabric.application.dto

import java.time.LocalDate

data class FabricCodeRequest(
    val registrationDate: String,
    val fabricTypeCode: String,
    val widthCode: String,
    val lengthCode: String,
) {
    companion object {
        fun of(registrationDate: LocalDate, fabricTypeCode: String, width: Long, length: Double): FabricCodeRequest {
            return FabricCodeRequest(
                registrationDate = registrationDate.year.toString().takeLast(2),
                fabricTypeCode = fabricTypeCode,
                widthCode = width.toString().padStart(3, '0'),
                lengthCode = length.toInt().toString().padStart(4, '0'),
            )
        }
    }
}
