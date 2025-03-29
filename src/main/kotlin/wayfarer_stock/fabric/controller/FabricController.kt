package wayfarer_stock.fabric.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import wayfarer_stock.fabric.application.FabricInfoFacade
import wayfarer_stock.fabric.controller.dto.FabricInfoRequest

@Tag(name = "Fabric", description = "Fabric Info API")
@RestController
@RequestMapping("/api/v1/fabric")
class FabricController(
    private val fabricInfoFacade: FabricInfoFacade,
) {
    @Operation(
        summary = "원단 등록",
        description = "새로운 원단 정보를 등록한다."
    )
    @PostMapping("/register")
    fun registerFabric(
        @Parameter(description = "등록할 원단 정보")
        @RequestBody req: FabricInfoRequest
    ) {
        fabricInfoFacade.registerFabric(req)
    }

    @Operation(
        summary = "원단 수정",
        description = "기존의 원단 정보를 수정한다."
    )
    @PutMapping("/{id}")
    fun updateFabric(
        @PathVariable id: Long,
        @Parameter(description = "수정할 원단 정보")
        @RequestBody request: FabricInfoRequest
    ) {
        fabricInfoFacade.updateFabric(id, request)
    }
}
