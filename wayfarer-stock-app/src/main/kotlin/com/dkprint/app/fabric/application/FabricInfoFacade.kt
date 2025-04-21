package com.dkprint.app.fabric.application

import com.dkprint.app.core.common.web.PagingResult
import com.dkprint.app.fabric.domain.entity.value.UsageStatus
import com.dkprint.app.fabric.domain.service.EditFabricInfoService
import com.dkprint.app.fabric.domain.service.FabricCodeService
import com.dkprint.app.fabric.domain.service.FabricInfoCountCacheService
import com.dkprint.app.fabric.domain.service.ReadFabricInfoService
import com.dkprint.app.fabric.domain.service.RegisterFabricInfoService
import com.dkprint.app.fabric.dto.request.FabricInfoRequest
import com.dkprint.app.fabric.dto.response.FabricInfoListResponse
import com.dkprint.app.fabric.dto.response.FabricInfoResponse
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class FabricInfoFacade(
    private val registerFabricInfoService: RegisterFabricInfoService,
    private val editFabricInfoService: EditFabricInfoService,
    private val readFabricInfoService: ReadFabricInfoService,
    private val fabricCodeService: FabricCodeService,
    private val fabricInfoCountCacheService: FabricInfoCountCacheService
) {
    fun registerFabric(fabricInfoRequest: FabricInfoRequest) {
        registerFabricInfoService.createFabricInfo(
            fabricInfoRequest,
            getOrdererId(fabricInfoRequest.ordererName),
            getCustomerId(fabricInfoRequest.customerName),
            getCodeId(fabricInfoRequest),
        )
        fabricInfoCountCacheService.evictAllCountsCache()
    }

    fun updateFabric(id: Long, fabricInfoRequest: FabricInfoRequest) {
        val fabricInfo = readFabricInfoService.getFabricInfo(id);
        editFabricInfoService.updateFabricInfo(
            fabricInfo,
            fabricInfoRequest,
            getOrdererId(fabricInfoRequest.ordererName),
            getCustomerId(fabricInfoRequest.customerName),
            getCodeId(fabricInfoRequest),
        )
    }

    fun deleteFabric(id: Long) {
        editFabricInfoService.deleteFabricInfo(id);
        fabricInfoCountCacheService.evictAllCountsCache()
    }

    fun deleteFabrics(ids: List<Long>) {
        editFabricInfoService.deleteMultipleFabricInfo(ids)
        fabricInfoCountCacheService.evictAllCountsCache()
    }

    fun getDetailedFabricInfo(id: Long): FabricInfoResponse {
        val fabricInfo = readFabricInfoService.getFabricInfo(id)
        return FabricInfoResponse.of(
            fabricInfo,
            getOrdererName(fabricInfo.ordererId),
            getCustomerName(fabricInfo.customerId),
            getCode(fabricInfo.codeId),
        )
    }

    fun getFabricInfoList(page: Int, size: Int): PagingResult<FabricInfoListResponse> {
        val pageRequest = PageRequest.of(page, size)
        val total = fabricInfoCountCacheService.getCachedTotalCount()
        val page = readFabricInfoService.getList(pageRequest, total).map {
            FabricInfoListResponse.of(
                it,
                getOrdererName(it.ordererId),
            )
        }
        return PagingResult.from(page)
    }

    fun getFabricInfoListByOrderer(
        page: Int,
        size: Int,
        startDate: LocalDate?,
        endDate: LocalDate?,
        ordererName: String
    ): PagingResult<FabricInfoListResponse> {
        val pageRequest = PageRequest.of(page, size)
        val ordererId = getOrdererId(ordererName)
        val page = readFabricInfoService.getListByOrderer(startDate, endDate, ordererId, pageRequest).map {
            FabricInfoListResponse.of(
                it,
                ordererName,
            )
        }
        return PagingResult.from(page)
    }

    fun getFabricInfoListByType(
        page: Int,
        size: Int,
        startDate: LocalDate?,
        endDate: LocalDate?,
        fabricTypeName: String
    ): PagingResult<FabricInfoListResponse> {
        val pageRequest = PageRequest.of(page, size)
        val pageResult =
            readFabricInfoService.getListByFabricType(startDate, endDate, fabricTypeName, pageRequest).map {
                FabricInfoListResponse.of(
                    it,
                    getOrdererName(it.ordererId),
                )
            }

        return PagingResult.from(pageResult)
    }

    fun getFabricInfoListByCode(
        page: Int,
        size: Int,
        startDate: LocalDate?,
        endDate: LocalDate?,
        fabricCode: String
    ): PagingResult<FabricInfoListResponse> {
        val pageRequest = PageRequest.of(page, size)
        val pageResult =
            readFabricInfoService.getListByFabricCode(startDate, endDate, fabricCode, pageRequest).map {
                FabricInfoListResponse.of(
                    it,
                    getOrdererName(it.ordererId),
                    fabricCode,
                )
            }

        return PagingResult.from(pageResult)
    }


    fun updateUsageStatus(id: Long, status: UsageStatus) {
        val fabricInfo = readFabricInfoService.getFabricInfo(id)
        editFabricInfoService.updateUsageStatus(fabricInfo, status)
    }


    private fun getOrdererId(ordererName: String): Long {
        return 1L // TODO: orderSdk.findIdByOrdererName(ordererName).orElseThrow { BadRequestException("wayfarer-stock.not-exist-ordererName") }
    }

    private fun getCustomerId(customerName: String): Long {
        return 1L // TODO: customerSdk.findIdByCustomerName(fabricInfoRequest.customerName).orElseThrow { BadRequestException("wayfarer-stock.not-exist-customerName") }
    }

    private fun getCodeId(fabricInfoRequest: FabricInfoRequest): Long {
        val fabricCode = fabricCodeService.createFabricCode(fabricInfoRequest)
        return 1L // TODO: codeSdk.createFabricCode(fabricCode).orElseThrow { BadRequestException("wayfarer-stock.not-valid-code") }
    }

    private fun getOrdererName(ordererId: Long): String {
        return "발주처 1" // TODO: orderSdk.getOrdererNameById(ordererId).orElseThrow { BadRequestException("wayfarer-stock.not-exist-ordererId") }
    }

    private fun getCustomerName(customerId: Long): String {
        return "고객사 1"// TODO: customerSdk.getCustomerNameById(customerId).orElseThrow { BadRequestException("wayfarer-stock.not-exist-customerId") }
    }

    private fun getCode(codeId: Long): String {
        return "25010990022" // TODO: codeSdk.getCodeById(fabricInfo.codeId).orElseThrow { BadRequestException("wayfarer-stock.not-exist-codeId) }
    }
}
