package com.dkprint.api.fabric.controller

import com.dkprint.api.ApiPath
import com.dkprint.app.fabric.dto.request.FabricInfoRequest
import com.dkprint.app.fabric.application.FabricInfoFacade
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Fabric", description = "Register Fabric Info API")
@RestController
class RegisterFabricController(
    private val fabricInfoFacade: FabricInfoFacade,
) {
    @Operation(
        summary = "원단 등록",
        description = "새로운 원단 정보를 등록한다."
    )
    @PostMapping(ApiPath.Fabric.REGISTER)
    fun registerFabric(
        @Parameter(description = "등록할 원단 정보")
        @RequestBody req: FabricInfoRequest
    ) {
        fabricInfoFacade.registerFabric(req)
    }
}
