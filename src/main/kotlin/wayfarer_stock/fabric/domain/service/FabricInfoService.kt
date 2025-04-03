package wayfarer_stock.fabric.domain.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import wayfarer_stock.fabric.application.dto.FabricInfoCreateRequest
import wayfarer_stock.fabric.domain.entity.FabricInfo
import wayfarer_stock.fabric.domain.repository.FabricInfoRepository

@Service
class FabricInfoService(
    private val fabricInfoRepository: FabricInfoRepository,
) {
    @Transactional
    fun createFabricInfo(fabricInfoCreateRequest: FabricInfoCreateRequest) {
        fabricInfoRepository.save(FabricInfo.of(fabricInfoCreateRequest))
    }

    @Transactional
    fun updateFabricInfo(id: Long, fabricInfoCreateRequest: FabricInfoCreateRequest) {
        val fabricInfo = getFabricInfo(id)
        fabricInfo.update(fabricInfoCreateRequest)
    }

    @Transactional
    fun deleteFabricInfo(id: Long) {
        fabricInfoRepository.deleteById(id)
    }

    private fun getFabricInfo(id: Long): FabricInfo {
        return fabricInfoRepository.findById(id).orElseThrow { IllegalArgumentException("존재하지 않는 원단 정보입니다. id: $id") }
    }
}
