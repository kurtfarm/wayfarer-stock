package wayfarer_stock.fabric.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import wayfarer_stock.fabric.domain.entity.FabricInfo

@Repository
interface FabricInfoRepository : JpaRepository<FabricInfo, Long> {
}
