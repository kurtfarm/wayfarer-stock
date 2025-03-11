package wayfarer_stock.fabric.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Id

@Embeddable
class Fabric(
    @Id
    @Column(name = "fabric_id")
    val fabricId: Long,

    @Column(name = "fabric_type")
    var fabricType: String, //TODO: ENUM으로 정의하는 게 나으려나?

    @Column(name = "width", nullable = false)
    var width: Long,

    @Column(name = "length", nullable = false)
    var length: Double,

    @Column(name = "thickness", nullable = false)
    var thickness: Double,

    @Column(name = "quantity", nullable = false)
    var quantity: Int,
)
