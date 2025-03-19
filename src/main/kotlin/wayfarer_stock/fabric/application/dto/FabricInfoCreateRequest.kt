package wayfarer_stock.fabric.application.dto

import wayfarer_stock.fabric.controller.dto.FabricInfoRequest
import java.time.LocalDate

data class FabricInfoCreateRequest(
    var registrationDate: LocalDate,
    var expectedArrivalDate: LocalDate,
    val ordererId: Long,
    val customerId: Long,
    val codeId: Long,
    var fabricTypeName: String,
    var width: Long,
    var length: Double,
    var thickness: Double,
    var quantity: Int,
    var comment: String?,
) {
    companion object {
        fun of(
            fabricInfoRequest: FabricInfoRequest,
            ordererId: Long,
            customerId: Long,
            codeId: Long
        ): FabricInfoCreateRequest {
            return FabricInfoCreateRequest(
                registrationDate = fabricInfoRequest.registrationDate,
                expectedArrivalDate = fabricInfoRequest.expectedArrivalDate,
                customerId = customerId,
                ordererId = ordererId,
                codeId = codeId,
                fabricTypeName = fabricInfoRequest.fabricTypeName,
                width = fabricInfoRequest.width,
                length = fabricInfoRequest.length,
                thickness = fabricInfoRequest.thickness,
                quantity = fabricInfoRequest.quantity,
                comment = fabricInfoRequest.comment,
            )
        }
    }
}
