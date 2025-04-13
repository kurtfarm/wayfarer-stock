package com.dkprint.app.fabric.domain.service

import com.dkprint.app.fabric.domain.repository.FabricInfoRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class FabricInfoCountCacheService(
    private val fabricInfoRepository: FabricInfoRepository
) {
    @Cacheable(
        value = ["fabricTypeCount"],
        key = "#fabricTypeName + '_' + (#startDate ?: 'null') + '_' + (#endDate ?: 'null')"
    )
    fun getCachedCount(
        startDate: LocalDate?,
        endDate: LocalDate?,
        fabricTypeName: String
    ): Long {
        return fabricInfoRepository.countByType(startDate, endDate, fabricTypeName)
    }
}
