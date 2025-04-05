package wayfarer_stock.fabric.controller.dto.response

import wayfarer_stock.core.util.DateFormatUtil
import wayfarer_stock.fabric.domain.entity.FabricInfo

data class FabricInfoListResponse(
    val id: Long,
    val fabricTypeName: String,
    val ordererName: String,
    val standard: Double,
    val width: Long,
    val thickness: Double,
    val quantity: Int,
    val expectedArrivalDate: String,
) {
    companion object {
        fun of(fabricInfo: FabricInfo, ordererName: String): FabricInfoListResponse {
            return FabricInfoListResponse(
                id = fabricInfo.id,
                fabricTypeName = fabricInfo.fabric.fabricTypeDetail,
                ordererName = ordererName,
                standard = fabricInfo.fabric.length * fabricInfo.fabric.width,
                width = fabricInfo.fabric.width,
                thickness = fabricInfo.fabric.thickness,
                quantity = fabricInfo.fabric.quantity,
                expectedArrivalDate = DateFormatUtil.formatWithDayOfWeek(fabricInfo.expectedArrivalDate),
            )
        }
    }
}
