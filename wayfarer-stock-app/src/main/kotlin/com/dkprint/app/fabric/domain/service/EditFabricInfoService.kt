package com.dkprint.app.fabric.domain.service

import com.dkprint.app.fabric.domain.entity.FabricInfo
import com.dkprint.app.fabric.domain.repository.FabricInfoRepository
import com.dkprint.app.fabric.dto.request.FabricInfoRequest
import jakarta.transaction.Transactional
import org.springframework.cache.annotation.CacheEvict
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
    @CacheEvict(
        value = ["fabricTypeCount"],
        allEntries = true
    )
    fun deleteFabricInfo(id: Long) {
        fabricInfoRepository.deleteById(id)
    }
}
