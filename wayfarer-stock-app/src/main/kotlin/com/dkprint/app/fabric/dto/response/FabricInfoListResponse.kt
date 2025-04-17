package com.dkprint.app.fabric.dto.response

import com.dkprint.app.core.util.DateFormatUtil
import com.dkprint.app.fabric.domain.entity.FabricInfo

data class FabricInfoListResponse(
    val id: Long,
    val fabricTypeName: String,
    val ordererName: String,
    val length: Double,
    val width: Long,
    val thickness: Double,
    val quantity: Int,
    val expectedArrivalDate: String,
    val isUsed: Boolean,
) {
    companion object {
        fun of(fabricInfo: FabricInfo, ordererName: String): FabricInfoListResponse {
            return FabricInfoListResponse(
                id = fabricInfo.id,
                fabricTypeName = fabricInfo.fabric.fabricTypeDetail,
                ordererName = ordererName,
                length = fabricInfo.fabric.length,
                width = fabricInfo.fabric.width,
                thickness = fabricInfo.fabric.thickness,
                quantity = fabricInfo.fabric.quantity,
                expectedArrivalDate = DateFormatUtil.formatWithDayOfWeek(fabricInfo.expectedArrivalDate),
                isUsed = fabricInfo.isUsed,
            )
        }
    }
}
