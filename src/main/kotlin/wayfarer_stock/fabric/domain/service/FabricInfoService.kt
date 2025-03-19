package wayfarer_stock.fabric.domain.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import wayfarer_stock.fabric.application.dto.FabricInfoCreateRequest
import wayfarer_stock.fabric.domain.entity.FabricInfo
import wayfarer_stock.fabric.domain.entity.FabricType
import wayfarer_stock.fabric.domain.repository.FabricInfoRepository

@Service
class FabricInfoService(
    private val fabricInfoRepository: FabricInfoRepository,
) {
    @Transactional
    fun createFabricInfo(fabricInfoCreateRequest: FabricInfoCreateRequest) {
        val fabricInfo = FabricInfo.from(fabricInfoCreateRequest)
        fabricInfoRepository.save(fabricInfo)
    }
}
