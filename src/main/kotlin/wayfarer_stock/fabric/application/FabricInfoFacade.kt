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
        val fabricInfoCreateRequest = convertToCreateRequest(fabricInfoRequest)
        fabricInfoService.createFabricInfo(fabricInfoCreateRequest)
    }

    fun updateFabric(id: Long, fabricInfoRequest: FabricInfoRequest) {
        val fabricInfoCreateRequest = convertToCreateRequest(fabricInfoRequest)
        fabricInfoService.updateFabricInfo(id, fabricInfoCreateRequest)
    }

    fun deleteFabric(id: Long) {
        fabricInfoService.deleteFabricInfo(id);
    }

    private fun convertToCreateRequest(fabricInfoRequest: FabricInfoRequest): FabricInfoCreateRequest {
        val ordererId = getOrdererId(fabricInfoRequest.ordererName)
        val customerId = getCustomerId(fabricInfoRequest.customerName)
        val codeId = getCodeId(fabricInfoRequest)
        return FabricInfoCreateRequest.of(fabricInfoRequest, ordererId, customerId, codeId)
    }

    private fun getOrdererId(ordererName: String): Long {
        return 1L // orderSdk.findIdByOrdererName(ordererName).orElseThrow { BadRequestException("존재하지 않는 발주처입니다: $ordererName") }
    }

    private fun getCustomerId(customerName: String): Long {
        return 1L // customerSdk.findIdByCustomerName(fabricInfoRequest.customerName).orElseThrow { BadRequestException("존재하지 않는 거래처입니다: $customerName") }
    }

    private fun createFabricCode(fabricInfoRequest: FabricInfoRequest): String {
        val fabricType = FabricType.getByTypeName(fabricInfoRequest.fabricTypeName)
        val fabricCodeRequest = FabricCodeRequest.of(
            fabricInfoRequest.registrationDate,
            fabricType.code,
            fabricInfoRequest.width,
            fabricInfoRequest.length
        );
        return fabricInfoService.createFabricCode(fabricCodeRequest);
    }

//    private fun updateFabricCode(codeId: Long, fabricInfoRequest: FabricInfoRequest){
//        val fabricCode = createFabricCode(fabricInfoRequest)
//        // codeSdk.updateFabric(codeId, fabricCode)
//    }

    private fun getCodeId(fabricInfoRequest: FabricInfoRequest): Long {
        val fabricCode = createFabricCode(fabricInfoRequest)
        return 1L // codeSdk.createFabricCode(fabricCode).orElseThrow { BadRequestException("원단 코드 생성에 실패했습니다.") }
    }
}
