package wayfarer_stock.fabric.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import wayfarer_stock.fabric.controller.dto.request.FabricInfoRequest

@Embeddable
class Fabric(
    @Enumerated(EnumType.STRING)
    @Column(name = "fabric_type", nullable = false)
    var fabricType: FabricType,

    @Column(name = "fabric_type_detail", nullable = false)
    var fabricTypeDetail: String,

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
        fun from(fabricInfoRequest: FabricInfoRequest): Fabric {
            val matchedType = FabricType.getByTypeName(fabricInfoRequest.fabricTypeName)
            val detail = (fabricInfoRequest.fabricTypeName.takeIf { matchedType == FabricType.DIRECT_INPUT }
                ?: matchedType.description)

            return Fabric(
                fabricType = FabricType.getByTypeName(fabricInfoRequest.fabricTypeName),
                fabricTypeDetail = detail,
                width = fabricInfoRequest.width,
                length = fabricInfoRequest.length,
                thickness = fabricInfoRequest.thickness,
                quantity = fabricInfoRequest.quantity
            )
        }
    }
}
