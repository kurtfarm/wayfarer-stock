package com.dkprint.app.fabric.dto.request

import com.dkprint.app.fabric.domain.entity.value.UsageStatus

data class FabricUsageStatusRequest(
    val status: UsageStatus
)
