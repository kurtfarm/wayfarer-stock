package wayfarer_stock.fabric.application

import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import wayfarer_stock.core.web.PagingResult
import wayfarer_stock.fabric.application.dto.FabricCodeRequest
import wayfarer_stock.fabric.application.dto.FabricInfoCreateRequest
import wayfarer_stock.fabric.controller.dto.request.FabricInfoRequest
import wayfarer_stock.fabric.controller.dto.response.FabricInfoListResponse
import wayfarer_stock.fabric.controller.dto.response.FabricInfoResponse
import wayfarer_stock.fabric.domain.entity.FabricType
import wayfarer_stock.fabric.domain.service.FabricCodeService
import wayfarer_stock.fabric.domain.service.FabricInfoService
import wayfarer_stock.fabric.domain.service.ReadFabricInfoService
import java.time.LocalDate

@Service
class FabricInfoFacade(
    private val fabricInfoService: FabricInfoService,
    private val fabricCodeService: FabricCodeService,
    private val readFabricInfoService: ReadFabricInfoService,
) {
    fun registerFabric(fabricInfoRequest: FabricInfoRequest) {
        val fabricInfoCreateRequest = convertToCreateRequest(fabricInfoRequest)
        fabricInfoService.createFabricInfo(fabricInfoCreateRequest)
    }

    fun updateFabric(id: Long, fabricInfoRequest: FabricInfoRequest) {
        val fabricInfoCreateRequest = convertToCreateRequest(fabricInfoRequest)
        val fabricInfo = readFabricInfoService.getFabricInfo(id);
        fabricInfoService.updateFabricInfo(fabricInfo, fabricInfoCreateRequest)
    }

    fun deleteFabric(id: Long) {
        fabricInfoService.deleteFabricInfo(id);
    }

    fun getDetailedFabricInfo(id: Long): FabricInfoResponse {
        val fabricInfo = readFabricInfoService.getFabricInfo(id)
        return FabricInfoResponse.of(
            fabricInfo,
            getOrdererName(fabricInfo.ordererId),
            readFabricInfoService.getFabricTypeName(fabricInfo.fabric),
            getCustomerName(fabricInfo.customerId),
            getCode(fabricInfo.codeId),
        )
    }

    fun getFabricInfoList(page: Int, size: Int): PagingResult<FabricInfoListResponse> {
        val pageRequest = PageRequest.of(page, size)
        val page = readFabricInfoService.getList(pageRequest).map {
            FabricInfoListResponse.of(
                it,
                readFabricInfoService.getFabricTypeName(it.fabric),
                getOrdererName(it.ordererId),
            )
        }
        return PagingResult.from(page)
    }

    fun getFabricInfoListByOrderer(
        page: Int,
        size: Int,
        startDate: LocalDate,
        endDate: LocalDate,
        ordererName: String
    ): PagingResult<FabricInfoListResponse> {
        val pageRequest = PageRequest.of(page, size)
        val ordererId = getOrdererId(ordererName)
        val page = readFabricInfoService.getListByOrderer(startDate, endDate, ordererId, pageRequest).map {
            FabricInfoListResponse.of(
                it,
                readFabricInfoService.getFabricTypeName(it.fabric),
                ordererName,
            )
        }
        return PagingResult.from(page)
    }

    fun getFabricInfoListByType(
        page: Int,
        size: Int,
        startDate: LocalDate,
        endDate: LocalDate,
        fabricTypeName: String
    ): PagingResult<FabricInfoListResponse> {
        val pageRequest = PageRequest.of(page, size)
        val page = readFabricInfoService.getListByFabricType(startDate, endDate, fabricTypeName, pageRequest).map {
            FabricInfoListResponse.of(
                it,
                readFabricInfoService.getFabricTypeName(it.fabric),
                getOrdererName(it.ordererId),
            )
        }
        return PagingResult.from(page)
    }

    private fun createFabricCode(fabricInfoRequest: FabricInfoRequest): String {
        val fabricType = FabricType.getByTypeName(fabricInfoRequest.fabricTypeName)
        val fabricCodeRequest = FabricCodeRequest.of(
            fabricInfoRequest.registrationDate,
            fabricType.code,
            fabricInfoRequest.width,
            fabricInfoRequest.length
        );
        return fabricCodeService.createFabricCode(fabricCodeRequest);
    }

    private fun convertToCreateRequest(fabricInfoRequest: FabricInfoRequest): FabricInfoCreateRequest {
        return FabricInfoCreateRequest.of(
            fabricInfoRequest,
            getOrdererId(fabricInfoRequest.ordererName),
            getCustomerId(fabricInfoRequest.customerName),
            getCodeId(fabricInfoRequest)
        )
    }

    private fun getOrdererId(ordererName: String): Long {
        return 1L // TODO: orderSdk.findIdByOrdererName(ordererName).orElseThrow { BadRequestException("존재하지 않는 발주처입니다: $ordererName") }
    }

    private fun getCustomerId(customerName: String): Long {
        return 1L // TODO: customerSdk.findIdByCustomerName(fabricInfoRequest.customerName).orElseThrow { BadRequestException("존재하지 않는 거래처입니다: $customerName") }
    }

    private fun getCodeId(fabricInfoRequest: FabricInfoRequest): Long {
        val fabricCode = createFabricCode(fabricInfoRequest)
        return 1L // TODO: codeSdk.createFabricCode(fabricCode).orElseThrow { BadRequestException("원단 코드 생성에 실패했습니다.") }
    }

    private fun getOrdererName(ordererId: Long): String {
        return "발주처 1" // TODO: orderSdk.getOrdererNameById(ordererId).orElseThrow { BadRequestException("존재하지 않는 고객사입니다: $ordererId") }
    }

    private fun getCustomerName(customerId: Long): String {
        return "고객사 1"// TODO: customerSdk.getCustomerNameById(customerId).orElseThrow { BadRequestException("존재하지 않는 거래처입니다: $customerId") }
    }

    private fun getCode(codeId: Long): String {
        return "25010990022" // TODO: codeSdk.getCodeById(fabricInfo.codeId).orElseThrow { BadRequestException("존재하지 않는 코드입니다: $codeId") }
    }
}
