package wayfarer_stock.fabric.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import wayfarer_stock.fabric.application.FabricInfoFacade
import wayfarer_stock.fabric.controller.dto.FabricInfoRequest

@RestController
@RequestMapping("/api/v1/fabric")
class FabricController(
    private val fabricInfoFacade: FabricInfoFacade,
) {
    @PostMapping("/register")
    fun registerFabric(@RequestBody req: FabricInfoRequest) {
        fabricInfoFacade.registerFabric(req)
    }
}
