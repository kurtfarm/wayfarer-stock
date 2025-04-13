package com.dkprint.app.fabric.domain.service

import com.dkprint.app.fabric.domain.repository.FabricInfoRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class FabricInfoCountCacheService(
    private val fabricInfoRepository: FabricInfoRepository
) {
    @Cacheable(
        value = ["fabricOrdererCount"],
        key = "{#startDate, #endDate, #ordererId}"
    )
    fun getCachedCountByOrderer(startDate: LocalDate?, endDate: LocalDate?, ordererId: Long): Long {
        return fabricInfoRepository.countByOrderer(startDate, endDate, ordererId)
    }

    @Cacheable(
        value = ["fabricTypeCount"],
        key = "#fabricTypeName + '_' + (#startDate ?: 'null') + '_' + (#endDate ?: 'null')"
    )
    fun getCachedCountByType(
        startDate: LocalDate?,
        endDate: LocalDate?,
        fabricTypeName: String
    ): Long {
        return fabricInfoRepository.countByType(startDate, endDate, fabricTypeName)
    }

    @Caching(
        evict = [
            CacheEvict(value = ["fabricOrdererCount"], allEntries = true),
            CacheEvict(value = ["fabricTypeCount"], allEntries = true)
        ]
    )
    fun evictAllCountsCache() {
    }
}
