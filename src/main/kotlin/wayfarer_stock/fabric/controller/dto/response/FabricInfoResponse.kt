package wayfarer_stock.fabric.controller.dto.response

import wayfarer_stock.fabric.domain.entity.FabricInfo
import java.time.LocalDate

data class FabricInfoResponse(
    val registrationDate: LocalDate,
    val expectedArrivalDate: LocalDate,
    val ordererName: String,
    val customerName: String,
    val fabricTypeName: String,
    val fabricCode: String,
    val width: Long,
    val length: Double,
    val thickness: Double,
    val quantity: Int,
    val comment: String?,
) {
    companion object {
        fun of(fabricInfo: FabricInfo, ordererName: String, customerName: String, fabricCode: String): FabricInfoResponse{
            return FabricInfoResponse(
                registrationDate = fabricInfo.registrationDate,
                expectedArrivalDate = fabricInfo.expectedArrivalDate,
                ordererName = ordererName,
                customerName= customerName,
                fabricTypeName = fabricInfo.fabric.fabricType.name,
                fabricCode = fabricCode,
                width = fabricInfo.fabric.width,
                length = fabricInfo.fabric.length,
                thickness = fabricInfo.fabric.thickness,
                quantity = fabricInfo.fabric.quantity,
                comment = fabricInfo.comment,
            )
        }
    }
}
