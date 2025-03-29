package wayfarer_stock.fabric.application

import org.springframework.stereotype.Service
import wayfarer_stock.fabric.application.dto.FabricCodeRequest
import wayfarer_stock.fabric.application.dto.FabricInfoCreateRequest
import wayfarer_stock.fabric.controller.dto.FabricInfoRequest
import wayfarer_stock.fabric.domain.entity.FabricType
import wayfarer_stock.fabric.domain.service.FabricInfoService

@Service
class FabricInfoFacade(
    private val fabricInfoService: FabricInfoService,
) {
    fun registerFabric(fabricInfoRequest: FabricInfoRequest) {
        val ordererId: Long = getOrdererId(fabricInfoRequest.ordererName)
        val customerId: Long = getCustomerId(fabricInfoRequest.customerName)
        val codeId: Long = getCodeId(fabricInfoRequest)
        val fabricInfoCreateRequest = FabricInfoCreateRequest.of(fabricInfoRequest, ordererId, customerId, codeId)
        fabricInfoService.createFabricInfo(fabricInfoCreateRequest)
    }

    private fun getOrdererId(ordererName: String): Long {
        return 1L // orderSdk.findIdByOrdererName(ordererName).orElseThrow { BadRequestException("존재하지 않는 발주처입니다: $ordererName") }
    }

    private fun getCustomerId(customerName: String): Long {
        return 1L // customerSdk.findIdByCustomerName(fabricInfoRequest.customerName).orElseThrow { BadRequestException("존재하지 않는 거래처입니다: $customerName") }
    }

    private fun getCodeId(fabricInfoRequest: FabricInfoRequest): Long {
        val fabricType = FabricType.getByTypeName(fabricInfoRequest.fabricTypeName)
        val fabricCodeRequest = FabricCodeRequest.of(
            fabricInfoRequest.registrationDate,
            fabricType.code,
            fabricInfoRequest.width,
            fabricInfoRequest.length
        );
        val fabricCode = fabricInfoService.createFabricCode(fabricCodeRequest);
        return 1L // codeSdk.createFabricCode(fabricCode).orElseThrow { BadRequestException("원단 코드 생성에 실패했습니다.") }
    }
}
