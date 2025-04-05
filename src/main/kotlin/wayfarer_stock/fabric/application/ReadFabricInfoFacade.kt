package wayfarer_stock.fabric.application

import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import wayfarer_stock.core.web.PagingResult
import wayfarer_stock.fabric.controller.dto.response.FabricInfoListResponse
import wayfarer_stock.fabric.controller.dto.response.FabricInfoResponse
import wayfarer_stock.fabric.domain.service.ReadFabricInfoService

@Service
class ReadFabricInfoFacade(
    private val readFabricInfoService: ReadFabricInfoService,
) {
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
        val page = readFabricInfoService.getList(pageRequest).map {
            FabricInfoListResponse.of(
                it,
                getOrdererName(it.ordererId)
            )
        }
        return PagingResult.from(page)
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
