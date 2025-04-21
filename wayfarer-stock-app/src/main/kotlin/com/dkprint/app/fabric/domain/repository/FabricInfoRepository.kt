package com.dkprint.app.fabric.domain.repository

import com.dkprint.app.fabric.domain.entity.FabricInfo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface FabricInfoRepository : JpaRepository<FabricInfo, Long> {
    fun findAllByOrderByIdDesc(pageable: Pageable): List<FabricInfo>

    @Query(
        """
    SELECT f FROM FabricInfo f
    WHERE (:startDate IS NULL OR f.registrationDate >= :startDate)
    AND (:endDate IS NULL OR f.registrationDate <= :endDate)
    AND f.ordererId = :ordererId
        """
    )
    fun searchByOrdererAndDate(
        @Param("startDate") startDate: LocalDate?,
        @Param("endDate") endDate: LocalDate?,
        @Param("ordererId") ordererId: Long,
        pageable: Pageable
    ): Page<FabricInfo>

    @Query(
        """
    SELECT f FROM FabricInfo f
    WHERE (:startDate IS NULL OR f.registrationDate >= :startDate)
    AND (:endDate IS NULL OR f.registrationDate <= :endDate)
    AND f.fabric.fabricTypeDetail LIKE %:fabricTypeName%
    AND f.fabric.fabricTypeDetail LIKE CONCAT(:fabricTypeName, '%')
        """
    )
    fun searchByFabricTypeNameAndDate(
        @Param("startDate") startDate: LocalDate?,
        @Param("endDate") endDate: LocalDate?,
        @Param("fabricTypeName") fabricTypeName: String,
        pageable: Pageable
    ): Page<FabricInfo>

    @Query(
        """
    SELECT f FROM FabricInfo f
    WHERE (:startDate IS NULL OR f.registrationDate >= :startDate)
    AND (:endDate IS NULL OR f.registrationDate <= :endDate)
    AND f.codeId = :codeId
        """
    )
    fun searchByFabricCodeAndDate(
        @Param("startDate") startDate: LocalDate?,
        @Param("endDate") endDate: LocalDate?,
        @Param("codeId") codeId: Long,
        pageable: Pageable
    ): Page<FabricInfo>


    @Query("SELECT COUNT(f) FROM FabricInfo f")
    fun countAll(): Long

    @Query(
        """
    SELECT COUNT(f) FROM FabricInfo f
    WHERE (:startDate IS NULL OR f.registrationDate >= :startDate)
    AND (:endDate IS NULL OR f.registrationDate <= :endDate)
    AND f.ordererId = :ordererId
    """
    )
    fun countByOrderer(
        @Param("startDate") startDate: LocalDate?,
        @Param("endDate") endDate: LocalDate?,
        @Param("ordererId") ordererId: Long
    ): Long

    @Query(
        """
    SELECT COUNT(f) FROM FabricInfo f
    WHERE (:startDate IS NULL OR f.registrationDate >= :startDate)
    AND (:endDate IS NULL OR f.registrationDate <= :endDate)
    AND f.fabric.fabricTypeDetail LIKE CONCAT(:fabricTypeName, '%')
    """
    )
    fun countByType(
        @Param("startDate") startDate: LocalDate?,
        @Param("endDate") endDate: LocalDate?,
        @Param("fabricTypeName") fabricTypeName: String,
    ): Long
}
