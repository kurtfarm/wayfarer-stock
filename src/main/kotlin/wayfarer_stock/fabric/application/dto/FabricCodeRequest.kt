package wayfarer_stock.fabric.application.dto

import wayfarer_stock.fabric.controller.dto.FabricInfoRequest
import java.time.LocalDate

data class FabricCodeRequest(
    val registrationDate: LocalDate,
    val fabricTypeCode: String,
    val width: Long,
    val length: Double,
) {
    companion object {
        fun of(fabricInfoRequest: FabricInfoRequest, fabricTypeCode: String): FabricCodeRequest {
            return FabricCodeRequest(
                registrationDate = fabricInfoRequest.registrationDate,
                fabricTypeCode = fabricTypeCode,
                width = fabricInfoRequest.width,
                length = fabricInfoRequest.length,
            )
        }
    }
}
