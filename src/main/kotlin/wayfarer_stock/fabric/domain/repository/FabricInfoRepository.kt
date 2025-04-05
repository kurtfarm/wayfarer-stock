package wayfarer_stock.fabric.domain.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import wayfarer_stock.fabric.domain.entity.FabricInfo
import java.time.LocalDate

@Repository
interface FabricInfoRepository : JpaRepository<FabricInfo, Long> {
    fun findAllByOrderByIdDesc(pageable: Pageable): Page<FabricInfo>

    @Query(
        """
    SELECT f FROM FabricInfo f
    WHERE f.registrationDate BETWEEN :startDate AND :endDate
    AND f.ordererId = :ordererId
    """
    )
    fun searchByOrdererAndDate(
        @Param("startDate") startDate: LocalDate,
        @Param("endDate") endDate: LocalDate,
        @Param("ordererId") ordererId: Long,
        pageable: Pageable
    ): Page<FabricInfo>

    @Query(
        """
        SELECT f FROM FabricInfo f
        WHERE f.registrationDate BETWEEN :startDate AND :endDate
        AND (
            f.fabric.fabricTypeDetail LIKE %:fabricTypeName%
        )
        """
    )
    fun searchByFabricTypeNameAndDate(
        @Param("startDate") startDate: LocalDate,
        @Param("endDate") endDate: LocalDate,
        @Param("fabricTypeName") fabricTypeName: String,
        pageable: Pageable
    ): Page<FabricInfo>
}
