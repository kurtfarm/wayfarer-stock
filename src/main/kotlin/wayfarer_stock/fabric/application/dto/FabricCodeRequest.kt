package wayfarer_stock.fabric.application.dto

import wayfarer_stock.fabric.controller.dto.FabricInfoRequest
import java.time.LocalDate

data class FabricCodeRequest(
    var registrationDate: LocalDate,
    var fabricTypeCode: String,
    var width: Long,
    var length: Double,
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
