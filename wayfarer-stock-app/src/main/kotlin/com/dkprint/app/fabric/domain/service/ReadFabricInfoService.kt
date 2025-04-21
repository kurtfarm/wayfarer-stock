package com.dkprint.app.fabric.domain.service

import com.dkprint.app.fabric.domain.entity.FabricInfo
import com.dkprint.app.fabric.domain.repository.FabricInfoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class ReadFabricInfoService(
    private val fabricInfoRepository: FabricInfoRepository
) {
    fun getList(pageable: Pageable, total: Long): Page<FabricInfo> {
        val content = fabricInfoRepository.findAllByOrderByIdDesc(pageable)
        return PageImpl(content, pageable, total)
    }

    @Transactional(readOnly = true)
    fun getListByOrderer(
        startDate: LocalDate?,
        endDate: LocalDate?,
        ordererId: Long,
        pageable: Pageable
    ): Page<FabricInfo> =
        fabricInfoRepository.searchByOrdererAndDate(startDate, endDate, ordererId, pageable)


    fun getListByFabricType(
        startDate: LocalDate?,
        endDate: LocalDate?,
        fabricTypeName: String,
        pageable: Pageable,
    ): Page<FabricInfo> =
        fabricInfoRepository.searchByFabricTypeNameAndDate(startDate, endDate, fabricTypeName, pageable)

    fun getListByFabricCode(
        startDate: LocalDate?,
        endDate: LocalDate?,
        codeId: Long,
        pageable: Pageable,
    ): Page<FabricInfo> =
        fabricInfoRepository.searchByFabricCodeAndDate(startDate, endDate, codeId, pageable)

    fun getFabricInfo(id: Long): FabricInfo {
        return fabricInfoRepository.findById(id).orElseThrow { IllegalArgumentException("존재하지 않는 원단 정보입니다. id: $id") }
    }
}
