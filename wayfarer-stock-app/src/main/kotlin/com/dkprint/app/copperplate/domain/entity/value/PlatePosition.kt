package com.dkprint.app.copperplate.domain.entity.value

import jakarta.persistence.Embeddable

@Embeddable
class PlatePosition(
    val index: Int,       // 0 ~ 9
    val color: String?
)
