package wayfarer_stock.fabric.domain.entity

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
    val id: Long = 0L,

    @Column(name = "registration_date", nullable = false)
    var registrationDate: LocalDate,

    @Column(name = "expected_arrival_date", nullable = false)
    var expectedArrivalDate: LocalDate,

    @Column(name = "orderer_id", nullable = false)
    var ordererId: Long,

    @Column(name = "customer_id", nullable = false)
    var customerId: Long,

    @Column(name = "fabric_code")
    val fabricCode: String,

    @Embedded
    var fabric: Fabric,

    @Column(name = "comment", nullable = true)
    var comment: String?,
) : BaseEntity() {

    private fun createFabricCode(): String {
        val registrationYear = registrationDate.year % 100
        val subCode = fabric.generateSubCode()

        return "$registrationYear$subCode"
    }
}
