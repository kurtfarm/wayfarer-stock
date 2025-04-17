package com.dkprint.app.fabric.domain.service

import com.dkprint.app.fabric.domain.entity.FabricInfo
import com.dkprint.app.fabric.domain.entity.value.UsageStatus
import com.dkprint.app.fabric.domain.repository.FabricInfoRepository
import com.dkprint.app.fabric.dto.request.FabricInfoRequest
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class EditFabricInfoService(
    private val fabricInfoRepository: FabricInfoRepository
) {
    @Transactional
    fun updateFabricInfo(
        fabricInfo: FabricInfo,
        fabricInfoRequest: FabricInfoRequest,
        ordererId: Long,
        customerId: Long,
        codeId: Long
    ) {
        fabricInfo.update(fabricInfoRequest, ordererId, customerId, codeId)
    }

    @Transactional
    fun deleteFabricInfo(id: Long) {
        fabricInfoRepository.deleteById(id)
    }

    @Transactional
    fun updateUsageStatus(fabricInfo: FabricInfo, status: UsageStatus) {
        fabricInfo.status = status
    }
}
