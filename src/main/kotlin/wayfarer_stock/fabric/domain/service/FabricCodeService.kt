package wayfarer_stock.fabric.domain.service

import org.springframework.stereotype.Service
import wayfarer_stock.fabric.controller.dto.request.FabricInfoRequest
import wayfarer_stock.fabric.domain.entity.FabricType

@Service
class FabricCodeService {
    fun createFabricCode(fabricInfoRequest: FabricInfoRequest): String {
        val fabricType = FabricType.getByTypeName(fabricInfoRequest.fabricTypeName)
        val registrationDate = fabricInfoRequest.registrationDate.year.toString().takeLast(2)
        val fabricTypeCode = fabricType.code
        val widthCode = fabricInfoRequest.width.toString().padStart(3, '0')
        val lengthCode = fabricInfoRequest.length.toInt().toString().padStart(4, '0')

        return registrationDate + fabricTypeCode + widthCode + lengthCode
    }

    // TODO: fabricInfo에 대한 수정이 있을 떄 CodeSdk에서 update할건지 codeId값은 그대로, 아니면 그냥 새로 insert하고 codeId값을 새로 받아올건지

    //    private fun updateFabricCode(codeId: Long, fabricInfoRequest: FabricInfoRequest){
    //        val fabricCode = createFabricCode(fabricInfoRequest)
    //        // codeSdk.updateFabric(codeId, fabricCode)
    //    }
}
