package wayfarer_stock.fabric.domain.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import wayfarer_stock.fabric.application.dto.FabricCodeRequest
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

    fun createFabricCode(fabricCodeRequest: FabricCodeRequest): String {
        return fabricCodeRequest.registrationDate +
                fabricCodeRequest.fabricTypeCode +
                fabricCodeRequest.widthCode +
                fabricCodeRequest.lengthCode
    }

    @Transactional
    fun updateFabricInfo(id: Long, fabricInfoCreateRequest: FabricInfoCreateRequest) {
        val fabricInfo = getFabricInfo(id)
        fabricInfo.update(fabricInfoCreateRequest)
    }

    fun getFabricInfo(id: Long): FabricInfo {
        return fabricInfoRepository.findById(id).orElseThrow()
    }
}
