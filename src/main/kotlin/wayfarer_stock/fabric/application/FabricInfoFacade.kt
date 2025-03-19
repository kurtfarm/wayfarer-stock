package wayfarer_stock.fabric.application

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import wayfarer_stock.fabric.application.dto.FabricCodeRequest
import wayfarer_stock.fabric.application.dto.FabricInfoCreateRequest
import wayfarer_stock.fabric.domain.service.FabricInfoService
import wayfarer_stock.fabric.controller.dto.FabricInfoRequest

@Service
class FabricInfoFacade(
    private val fabricInfoService: FabricInfoService,
) {
    @Transactional
    fun create(fabricInfoRequest: FabricInfoRequest) {
        val ordererId: Long = getOrdererId(fabricInfoRequest.ordererName)
        val customerId: Long = getCustomerId(fabricInfoRequest.customerName)
        val codeId: Long = createCodeId(fabricInfoRequest)
        val fabricInfoCreateRequest = FabricInfoCreateRequest.of(fabricInfoRequest, ordererId, customerId, codeId)
        fabricInfoService.create(fabricInfoCreateRequest)
    }

    private fun getOrdererId(ordererName: String): Long {
        return 1L // orderSdk.findIdByOrdererName(fabricInfoRequest.ordererName)
    }

    private fun getCustomerId(customerName: String): Long {
        return 1L // customerSdk.findIdByCustomerName(fabricInfoRequest.customerName)
    }

    private fun createCodeId(fabricInfoRequest: FabricInfoRequest): Long {
        val fabricTypeCode = fabricInfoService.createTypeCode(fabricInfoRequest.fabricTypeName);
        val fabricCodeRequest = FabricCodeRequest.of(fabricInfoRequest, fabricTypeCode);
        return 1L // codeSdk.createFabricCode(fabricCodeRequest)
    }
}
