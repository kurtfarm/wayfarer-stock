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
    @Transactional(readOnly = true)
    fun getList(pageable: Pageable): Page<FabricInfo> {
        return fabricInfoRepository.findAllByOrderByIdDesc(pageable)
    }

    @Transactional(readOnly = true)
    fun getListByOrderer(
        startDate: LocalDate?,
        endDate: LocalDate?,
        ordererId: Long,
        pageable: Pageable,
        total: Long
    ): Page<FabricInfo> {
        val content = fabricInfoRepository.findSliceByOrderer(startDate, endDate, ordererId, pageable)

        return PageImpl(content, pageable, total)
    }

    fun getListByFabricType(
        startDate: LocalDate?,
        endDate: LocalDate?,
        fabricTypeName: String,
        pageable: Pageable,
        total: Long
    ): Page<FabricInfo> {
        val content = fabricInfoRepository.findSliceByType(startDate, endDate, fabricTypeName, pageable)

        return PageImpl(content, pageable, total)
    }

    fun getFabricInfo(id: Long): FabricInfo {
        return fabricInfoRepository.findById(id).orElseThrow { IllegalArgumentException("존재하지 않는 원단 정보입니다. id: $id") }
    }
}
