package wayfarer_stock.fabric.controller

import ApiPath
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import wayfarer_stock.core.web.PagingResult
import wayfarer_stock.fabric.application.ReadFabricInfoFacade
import wayfarer_stock.fabric.controller.dto.response.FabricInfoListResponse
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

    @Operation(
        summary = "원단 리스트 불러오기",
        description = "원단 초기 리스트를 불러온다."
    )
    @GetMapping(ApiPath.Fabric.READ_FABRIC_LIST)
    fun readFabricInfoList(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "15") size: Int,
    ): PagingResult<FabricInfoListResponse> {
        return readFabricInfoFacade.getFabricInfoList(page, size)
    }
}
