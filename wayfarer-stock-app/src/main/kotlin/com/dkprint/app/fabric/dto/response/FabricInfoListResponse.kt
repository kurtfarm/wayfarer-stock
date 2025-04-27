package com.dkprint.app.fabric.dto.response

import com.dkprint.app.core.util.DateFormatUtil
import com.dkprint.app.fabric.domain.entity.FabricInfo
import com.dkprint.app.fabric.domain.entity.value.UsageStatus

data class FabricInfoListResponse(
    val id: Long,
    val fabricTypeName: String,
    val fabricCode: String,
    val ordererName: String,
    val length: Double,
    val width: Long,
    val thickness: Double,
    val quantity: Int,
    val expectedArrivalDate: String,
    val status: UsageStatus,
) {
    companion object {
        fun of(fabricInfo: FabricInfo, ordererName: String, fabricCode: String, ): FabricInfoListResponse {
            return FabricInfoListResponse(
                id = fabricInfo.id,
                fabricTypeName = fabricInfo.fabric.fabricTypeDetail,
                fabricCode = fabricCode,
                ordererName = ordererName,
                length = fabricInfo.fabric.length,
                width = fabricInfo.fabric.width,
                thickness = fabricInfo.fabric.thickness,
                quantity = fabricInfo.fabric.quantity,
                expectedArrivalDate = DateFormatUtil.formatWithDayOfWeek(fabricInfo.expectedArrivalDate),
                status = fabricInfo.status,
            )
        }
    }
}
