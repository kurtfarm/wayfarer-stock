package com.dkprint.app.fabric.dto.request

import java.time.LocalDate

data class FabricInfoRequest (
    val registrationDate: LocalDate,
    val expectedArrivalDate: LocalDate,
    val ordererName: String,
    val customerName: String,
    val fabricTypeName: String,
    val width: Long,
    val length: Double,
    val thickness: Double,
    val quantity: Int,
    val comment: String?,
)
