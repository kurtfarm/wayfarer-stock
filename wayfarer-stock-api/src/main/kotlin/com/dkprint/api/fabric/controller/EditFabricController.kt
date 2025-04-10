package com.dkprint.api.fabric.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import wayfarer_stock.fabric.application.FabricInfoFacade
import wayfarer_stock.fabric.controller.dto.request.FabricInfoRequest

@Tag(name = "Fabric", description = "Edit Fabric Info API")
@RestController
class EditFabricController (
    private val fabricInfoFacade: FabricInfoFacade,
){
    @Operation(
        summary = "원단 수정",
        description = "기존의 원단 정보를 수정한다."
    )
    @PutMapping(ApiPath.Fabric.EDIT)
    fun updateFabric(
        @PathVariable id: Long,
        @Parameter(description = "수정할 원단 정보")
        @RequestBody request: FabricInfoRequest
    ) {
        fabricInfoFacade.updateFabric(id, request)
    }

    @Operation(
        summary = "원단 삭제",
        description = "기존의 원단 정보를 삭제한다."
    )
    @DeleteMapping(ApiPath.Fabric.DELETE)
    fun deleteFabric(
        @Parameter(description = "삭제할 원단 id")
        @PathVariable id: Long,
    ) {
        fabricInfoFacade.deleteFabric(id)
    }
}
