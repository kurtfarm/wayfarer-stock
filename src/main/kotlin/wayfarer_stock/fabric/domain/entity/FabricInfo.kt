package wayfarer_stock.fabric.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import wayfarer_stock.core.infrastructure.jpa.shared.BaseEntity
import wayfarer_stock.fabric.application.dto.FabricInfoCreateRequest
import wayfarer_stock.fabric.core.AggregateRoot
import java.time.LocalDate

@AggregateRoot
@Entity
@Table(name = "fabric_info")
class FabricInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0L,

    @Column(name = "registration_date", nullable = false)
    var registrationDate: LocalDate,

    @Column(name = "expected_arrival_date", nullable = false)
    var expectedArrivalDate: LocalDate,

    @Column(name = "orderer_id", nullable = false)
    var ordererId: Long,

    @Column(name = "customer_id", nullable = false)
    var customerId: Long,

    @Column(name = "code_id", nullable = false)
    val codeId: Long,

    @Embedded
    var fabric: Fabric,

    @Column(name = "comment", nullable = true)
    var comment: String?,
) : BaseEntity() {
    companion object {
        fun from(fabricInfoCreateRequest: FabricInfoCreateRequest): FabricInfo {
            return FabricInfo(
                registrationDate = fabricInfoCreateRequest.registrationDate,
                expectedArrivalDate = fabricInfoCreateRequest.expectedArrivalDate,
                ordererId = fabricInfoCreateRequest.ordererId,
                customerId = fabricInfoCreateRequest.customerId,
                codeId = fabricInfoCreateRequest.codeId,
                fabric = Fabric.from(fabricInfoCreateRequest),
                comment = fabricInfoCreateRequest.comment,
            )

        }
    }
}
