package wayfarer_stock.fabric.controller

import ApiPath
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import wayfarer_stock.fabric.application.FabricInfoFacade
import wayfarer_stock.fabric.controller.dto.request.FabricInfoRequest

@Tag(name = "Fabric", description = "Register Fabric Info API")
@RestController
class RegisterFabricController(
    private val fabricInfoFacade: FabricInfoFacade,
) {
    @Operation(
        summary = "원단 등록",
        description = "새로운 원단 정보를 등록한다."
    )
    @PostMapping(ApiPath.Fabric.REGISTER_FABRIC)
    fun registerFabric(
        @Parameter(description = "등록할 원단 정보")
        @RequestBody req: FabricInfoRequest
    ) {
        fabricInfoFacade.registerFabric(req)
    }
}
