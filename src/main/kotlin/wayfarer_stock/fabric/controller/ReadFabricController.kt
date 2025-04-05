package wayfarer_stock.fabric.controller

import ApiPath
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import wayfarer_stock.fabric.application.ReadFabricInfoFacade
import wayfarer_stock.fabric.controller.dto.response.FabricInfoResponse

@Tag(name = "Fabric", description = "Read Fabric Info API")
@RestController
class ReadFabricController (
    private val readFabricInfoFacade: ReadFabricInfoFacade
){
    @Operation(
        summary = "원단 상세 값 불러오기",
        description = "원단을 조회할 때 상세 값들을 불러온다."
    )
    @GetMapping(ApiPath.Fabric.READ_DETAILED_FABRIC_INFO)
    fun readDetailedFabricInfo(@PathVariable id: Long): ResponseEntity<FabricInfoResponse> {
        return ResponseEntity.ok(readFabricInfoFacade.getDetailedFabricInfo(id));
    }
}
