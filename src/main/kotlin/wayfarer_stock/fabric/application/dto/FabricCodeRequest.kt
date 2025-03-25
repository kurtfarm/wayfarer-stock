package wayfarer_stock.fabric.application.dto

import wayfarer_stock.fabric.controller.dto.FabricInfoRequest

data class FabricCodeRequest(
    val registrationDate: String,
    val fabricTypeCode: String,
    val widthCode: String,
    val lengthCode: String,
) {
    companion object {
        fun of(fabricInfoRequest: FabricInfoRequest, fabricTypeCode: String): FabricCodeRequest {
            return FabricCodeRequest(
                registrationDate = fabricInfoRequest.registrationDate.year.toString().takeLast(2),
                fabricTypeCode = fabricTypeCode,
                widthCode = fabricInfoRequest.width.toString().padStart(3, '0'),
                lengthCode = fabricInfoRequest.length.toInt().toString().padStart(4, '0'),
            )
        }
    }
}
