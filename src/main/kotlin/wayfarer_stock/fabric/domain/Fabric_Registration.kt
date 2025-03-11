package wayfarer_stock.fabric.domain

import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import wayfarer_stock.core.infrastructure.jpa.shared.BaseEntity
import wayfarer_stock.fabric.core.AggregateRoot
import java.time.LocalDate

@AggregateRoot
@Entity
@Table(name = "fabric_registration")
class FabricRegistration(
    @Id
    @Column(name = "id")
    val id: Long,

    @Column(name = "registration_date", nullable = false)
    var registrationDate: LocalDate,

    @Column(name = "expected_arrivcal_date", nullable = false)
    var expectedArrivalDate: LocalDate,

    @Column(name = "orderer_id", nullable = false)
    var ordererId: Long,

    @Column(name = "customer_id", nullable = false)
    var customerId: Long,

    @Embedded
    var fabric: Fabric,

    @Column(name = "comment", nullable = true)
    var comment: String?,
) : BaseEntity() {
}
