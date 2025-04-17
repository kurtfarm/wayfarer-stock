package com.dkprint.app.fabric.domain.entity

import com.dkprint.app.core.AggregateRoot
import com.dkprint.app.core.infrastructure.jpa.shared.BaseEntity
import com.dkprint.app.fabric.dto.request.FabricInfoRequest
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import java.time.LocalDate

@AggregateRoot
@Entity
@SQLDelete(sql = "UPDATE fabric_info SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@Table(
    name = "fabric_info",
    indexes = [
        Index(name = "idx_fabric_type_detail", columnList = "fabric_type_detail")
    ]
)
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
    var codeId: Long,

    @Embedded
    var fabric: Fabric,

    @Column(name = "comment", nullable = true)
    var comment: String?,

    @Column(name = "is_used", nullable = true)
    var isUsed: Boolean = false,
) : BaseEntity() {
    companion object {
        fun of(fabricInfoRequest: FabricInfoRequest, ordererId: Long, customerId: Long, codeId: Long): FabricInfo {
            return FabricInfo(
                registrationDate = fabricInfoRequest.registrationDate,
                expectedArrivalDate = fabricInfoRequest.expectedArrivalDate,
                ordererId = ordererId,
                customerId = customerId,
                codeId = codeId,
                fabric = Fabric.from(fabricInfoRequest),
                comment = fabricInfoRequest.comment,
            )
        }
    }

    fun update(fabricInfoRequest: FabricInfoRequest, ordererId: Long, customerId: Long, codeId: Long) {
        this.registrationDate = fabricInfoRequest.registrationDate
        this.expectedArrivalDate = fabricInfoRequest.expectedArrivalDate
        this.ordererId = ordererId
        this.customerId = customerId
        this.codeId = codeId
        this.fabric = Fabric.from(fabricInfoRequest)
        this.comment = fabricInfoRequest.comment
    }
}
