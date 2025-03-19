package wayfarer_stock.fabric.controller.dto

import java.time.LocalDate

data class FabricInfoRequest (
    var registrationDate: LocalDate,
    var expectedArrivalDate: LocalDate,
    var ordererName: String,
    var customerName: String,
    var fabricTypeName: String,
    var width: Long,
    var length: Double,
    var thickness: Double,
    var quantity: Int,
    var comment: String?,
)
