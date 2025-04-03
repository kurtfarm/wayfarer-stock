package wayfarer_stock.fabric.domain.service

import org.springframework.stereotype.Service
import wayfarer_stock.fabric.application.dto.FabricCodeRequest

@Service
class FabricCodeService {
    fun createFabricCode(fabricCodeRequest: FabricCodeRequest): String {
        return fabricCodeRequest.registrationDate +
                fabricCodeRequest.fabricTypeCode +
                fabricCodeRequest.widthCode +
                fabricCodeRequest.lengthCode
    }

    // TODO: fabricInfo에 대한 수정이 있을 떄 CodeSdk에서 update할건지 codeId값은 그대로, 아니면 그냥 새로 insert하고 codeId값을 새로 받아올건지

    //    private fun updateFabricCode(codeId: Long, fabricInfoRequest: FabricInfoRequest){
    //        val fabricCode = createFabricCode(fabricInfoRequest)
    //        // codeSdk.updateFabric(codeId, fabricCode)
    //    }
}
