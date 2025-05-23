package com.dkprint.api.controller.fabric

import com.dkprint.api.ApiPath
import com.dkprint.app.fabric.dto.response.FabricInfoListResponse
import com.dkprint.app.fabric.dto.response.FabricInfoResponse
import com.dkprint.app.core.common.web.PagingResult
import com.dkprint.app.fabric.application.FabricInfoFacade
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@Tag(name = "Fabric (READ)", description = "Read Fabric Info API")
@RestController
class ReadFabricController(
    private val fabricInfoFacade: FabricInfoFacade,
) {
    @Operation(
        summary = "원단 상세 값 불러오기",
        description = "원단을 조회할 때 상세 값들을 불러온다."
    )
    @GetMapping(ApiPath.Fabric.GET_DETAILED_INFO)
    fun readDetailedFabricInfo(@PathVariable id: Long): ResponseEntity<FabricInfoResponse> {
        return ResponseEntity.ok(fabricInfoFacade.getDetailedFabricInfo(id));
    }

    @Operation(
        summary = "원단 리스트 불러오기",
        description = "원단 초기 리스트를 불러온다."
    )
    @GetMapping(ApiPath.Fabric.GET_LIST)
    fun readFabricInfoList(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "15") size: Int,
    ): PagingResult<FabricInfoListResponse> {
        return fabricInfoFacade.getFabricInfoList(page, size)
    }

    @Operation(
        summary = "원단 리스트 검색 (기준: 거래처)",
        description = "거래처를 기준으로 원단 리스트를 검색한다."
    )
    @GetMapping(ApiPath.Fabric.SEARCH_BY_ORDERER)
    fun readFabricInfoListByOrderer(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "15") size: Int,
        @RequestParam(required = false) startDate: LocalDate?,
        @RequestParam(required = false) endDate: LocalDate?,
        @RequestParam ordererName: String,
    ): PagingResult<FabricInfoListResponse> {
        return fabricInfoFacade.getFabricInfoListByOrderer(page, size, startDate, endDate, ordererName)
    }

    @Operation(
        summary = "원단 리스트 검색 (기준: 원단구분)",
        description = "원단구분을 기준으로 원단 리스트를 검색한다."
    )
    @GetMapping(ApiPath.Fabric.SEARCH_BY_TYPE)
    fun readFabricInfoListByType(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "15") size: Int,
        @RequestParam(required = false) startDate: LocalDate?,
        @RequestParam(required = false) endDate: LocalDate?,
        @RequestParam fabricTypeName: String,
    ): PagingResult<FabricInfoListResponse> {
        return fabricInfoFacade.getFabricInfoListByType(page, size, startDate, endDate, fabricTypeName)
    }

    @Operation(
        summary = "원단 리스트 검색 (기준: 원단코드)",
        description = "원단코드를 기준으로 원단 리스트를 검색한다."
    )
    @GetMapping(ApiPath.Fabric.SEARCH_BY_CODE)
    fun readFabricInfoListByCode(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "15") size: Int,
        @RequestParam(required = false) startDate: LocalDate?,
        @RequestParam(required = false) endDate: LocalDate?,
        @RequestParam fabricCode: String,
    ): PagingResult<FabricInfoListResponse> {
        return fabricInfoFacade.getFabricInfoListByCode(page, size, startDate, endDate, fabricCode)
    }
}
