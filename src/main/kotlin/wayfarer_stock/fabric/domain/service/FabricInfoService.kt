package wayfarer_stock.fabric.domain.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import wayfarer_stock.fabric.controller.dto.request.FabricInfoRequest
import wayfarer_stock.fabric.domain.entity.FabricInfo
import wayfarer_stock.fabric.domain.repository.FabricInfoRepository

@Service
class FabricInfoService(
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
}
