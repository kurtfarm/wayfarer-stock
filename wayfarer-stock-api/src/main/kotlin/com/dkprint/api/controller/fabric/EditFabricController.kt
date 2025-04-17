package com.dkprint.api.controller.fabric

import com.dkprint.api.ApiPath
import com.dkprint.app.core.common.web.response.ApiResponse
import com.dkprint.app.fabric.application.FabricInfoFacade
import com.dkprint.app.fabric.dto.request.FabricInfoRequest
import com.dkprint.app.fabric.dto.request.FabricUsageStatusRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Fabric", description = "Edit Fabric Info API")
@RestController
class EditFabricController(
    private val fabricInfoFacade: FabricInfoFacade,
) {
    @Operation(
        summary = "원단 수정",
        description = "기존의 원단 정보를 수정한다."
    )
    @PutMapping(ApiPath.Fabric.EDIT)
    fun updateFabric(
        @PathVariable id: Long,
        @Parameter(description = "수정할 원단 정보")
        @RequestBody request: FabricInfoRequest
    ): ApiResponse<Boolean> {
        fabricInfoFacade.updateFabric(id, request)
        return ApiResponse.success(true)
    }

    @Operation(
        summary = "원단 삭제",
        description = "기존의 원단 정보를 삭제한다."
    )
    @DeleteMapping(ApiPath.Fabric.DELETE)
    fun deleteFabric(
        @Parameter(description = "삭제할 원단 id")
        @PathVariable id: Long,
    ): ApiResponse<Boolean> {
        fabricInfoFacade.deleteFabric(id)
        return ApiResponse.success(true)
    }

    @Operation(
        summary = "원단 상태 정보 변경",
        description = "의뢰서/사양서에서 원단이 사용되는 경우 상태를 ACTIVE, 사용되지 않는 경우에는 INACTIVE로 변경"
    )
    @PatchMapping("/{id}/usage-status")
    fun updateUsageStatus(
        @PathVariable id: Long,
        @RequestBody request: FabricUsageStatusRequest
    ): ApiResponse<Boolean> {
        fabricInfoFacade.updateUsageStatus(id, request.status)
        return ApiResponse.success(true)
    }

}
