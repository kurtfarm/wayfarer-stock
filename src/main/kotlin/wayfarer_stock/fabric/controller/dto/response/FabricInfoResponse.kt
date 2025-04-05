package wayfarer_stock.fabric.controller.dto.response

import wayfarer_stock.core.util.DateFormatUtil
import wayfarer_stock.fabric.domain.entity.FabricInfo
import wayfarer_stock.fabric.domain.entity.FabricType

data class FabricInfoResponse(
    val registrationDate: String,
    val expectedArrivalDate: String,
    val ordererName: String,
    val fabricTypeName: String,
    val fabricCode: String,
    val customerName: String,
    val standard: Double,
    val width: Long,
    val thickness: Double,
    val quantity: Int,
    val comment: String?,
) {
    companion object {
        fun of(
            fabricInfo: FabricInfo,
            ordererName: String,
            fabricTypeName: String,
            customerName: String,
            fabricCode: String
        ): FabricInfoResponse {

            return FabricInfoResponse(
                registrationDate = DateFormatUtil.formatDate(fabricInfo.registrationDate),
                expectedArrivalDate = DateFormatUtil.formatDate(fabricInfo.expectedArrivalDate),
                ordererName = ordererName,
                fabricTypeName = fabricTypeName,
                fabricCode = fabricCode,
                customerName = customerName,
                standard = fabricInfo.fabric.length * fabricInfo.fabric.width,
                width = fabricInfo.fabric.width,
                thickness = fabricInfo.fabric.thickness,
                quantity = fabricInfo.fabric.quantity,
                comment = fabricInfo.comment,
            )
        }
    }

}
