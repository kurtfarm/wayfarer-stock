package wayfarer_stock.fabric.domain.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import wayfarer_stock.fabric.domain.entity.FabricInfo
import wayfarer_stock.fabric.domain.repository.FabricInfoRepository
import java.time.LocalDate

@Service
class ReadFabricInfoService(
    private val fabricInfoRepository: FabricInfoRepository
) {
    fun getFabricInfo(id: Long): FabricInfo {
        return fabricInfoRepository.findById(id).orElseThrow { IllegalArgumentException("존재하지 않는 원단 정보입니다. id: $id") }
    }

    @Transactional(readOnly = true)
    fun getList(pageable: Pageable): Page<FabricInfo> {
        return fabricInfoRepository.findAllByOrderByIdDesc(pageable)
    }

    @Transactional(readOnly = true)
    fun getListByOrderer(
        startDate: LocalDate,
        endDate: LocalDate,
        ordererId: Long,
        pageable: Pageable
    ): Page<FabricInfo> {
        return fabricInfoRepository.searchByOrdererAndDate(startDate, endDate, ordererId, pageable)
    }
}
