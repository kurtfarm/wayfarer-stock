package wayfarer_stock.fabric.application.dto

import wayfarer_stock.fabric.controller.dto.request.FabricInfoRequest
import java.time.LocalDate

data class FabricInfoCreateRequest(
    val registrationDate: LocalDate,
    val expectedArrivalDate: LocalDate,
    val ordererId: Long,
    val customerId: Long,
    val codeId: Long,
    val fabricTypeName: String,
    val width: Long,
    val length: Double,
    val thickness: Double,
    val quantity: Int,
    val comment: String?,
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
