package wayfarer_stock.fabric.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import wayfarer_stock.fabric.application.dto.FabricInfoCreateRequest

@Embeddable
class Fabric(

    @Enumerated(EnumType.STRING)
    @Column(name = "fabric_type", nullable = false)
    var fabricType: FabricType,

    @Column(name = "fabric_type_detail", nullable = true)
    var fabricTypeDetail: String?,

    @Column(name = "width", nullable = false)
    var width: Long,

    @Column(name = "length", nullable = false)
    var length: Double,

    @Column(name = "thickness", nullable = false)
    var thickness: Double,

    @Column(name = "quantity", nullable = false)
    var quantity: Int,
) {
    companion object {
        fun from(fabricInfoCreateRequest: FabricInfoCreateRequest): Fabric {
            val matchedType = FabricType.getByTypeName(fabricInfoCreateRequest.fabricTypeName)
            val detail = if (matchedType == FabricType.DIRECT_INPUT) fabricInfoCreateRequest.fabricTypeName else null

            return Fabric(
                fabricType = FabricType.getByTypeName(fabricInfoCreateRequest.fabricTypeName),
                fabricTypeDetail = detail,
                width = fabricInfoCreateRequest.width,
                length = fabricInfoCreateRequest.length,
                thickness = fabricInfoCreateRequest.thickness,
                quantity = fabricInfoCreateRequest.quantity
            )
        }
    }
}
