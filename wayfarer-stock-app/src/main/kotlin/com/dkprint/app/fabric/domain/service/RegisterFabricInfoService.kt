package com.dkprint.app.fabric.domain.service

import com.dkprint.app.fabric.domain.entity.FabricInfo
import com.dkprint.app.fabric.domain.repository.FabricInfoRepository
import com.dkprint.app.fabric.dto.request.FabricInfoRequest
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class RegisterFabricInfoService(
    private val fabricInfoRepository: FabricInfoRepository,
) {
    @Transactional
    fun createFabricInfo(
        fabricInfoRequest: FabricInfoRequest,
        ordererId: Long,
        customerId: Long,
        codeId: Long,
    ) {
        fabricInfoRepository.save(
            FabricInfo.of(fabricInfoRequest, ordererId, customerId, codeId)
        )
    }
}
