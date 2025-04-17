package com.dkprint.app.fabric.dto.response

import com.dkprint.app.core.util.DateFormatUtil
import com.dkprint.app.fabric.domain.entity.FabricInfo

data class FabricInfoResponse(
    val registrationDate: String,
    val expectedArrivalDate: String,
    val ordererName: String,
    val fabricTypeName: String,
    val fabricCode: String,
    val customerName: String,
    val length: Double,
    val width: Long,
    val thickness: Double,
    val quantity: Int,
    val comment: String?,
    val isUsed: Boolean,
) {
    companion object {
        fun of(
            fabricInfo: FabricInfo,
            ordererName: String,
            customerName: String,
            fabricCode: String
        ): FabricInfoResponse {

            return FabricInfoResponse(
                registrationDate = DateFormatUtil.formatDate(fabricInfo.registrationDate),
                expectedArrivalDate = DateFormatUtil.formatDate(fabricInfo.expectedArrivalDate),
                ordererName = ordererName,
                fabricTypeName = fabricInfo.fabric.fabricTypeDetail,
                fabricCode = fabricCode,
                customerName = customerName,
                length = fabricInfo.fabric.length,
                width = fabricInfo.fabric.width,
                thickness = fabricInfo.fabric.thickness,
                quantity = fabricInfo.fabric.quantity,
                comment = fabricInfo.comment,
                isUsed = fabricInfo.isUsed,
            )
        }
    }

}
