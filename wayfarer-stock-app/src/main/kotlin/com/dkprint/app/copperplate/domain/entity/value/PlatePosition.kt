package com.dkprint.app.copperplate.domain.entity.value

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class PlatePosition(
    @Column(name = "position_index")
    val positionIndex: Int,

    @Column(name = "color")
    val color: String?
)
