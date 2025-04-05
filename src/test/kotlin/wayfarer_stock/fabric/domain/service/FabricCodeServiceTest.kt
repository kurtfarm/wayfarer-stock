package wayfarer_stock.fabric.domain.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import wayfarer_stock.fabric.controller.dto.request.FabricInfoRequest
import wayfarer_stock.fabric.domain.repository.FabricInfoRepository
import java.time.LocalDate
import kotlin.test.Test

@ExtendWith(MockitoExtension::class)
class FabricCodeServiceTest {

    @Mock
    private lateinit var fabricInfoRepository: FabricInfoRepository

    @InjectMocks
    private lateinit var fabricCodeService: FabricCodeService

    @Test
    fun `올바른 값이 입력되면 코드가 생성된다`() {
        // given
        val fabricInfoRequest = createFakeRequest()

        // when
        val result = fabricCodeService.createFabricCode(fabricInfoRequest)

        // then
        assertThat(result).isEqualTo("25011500200")
    }

    private fun createFakeRequest(): FabricInfoRequest {
        return FabricInfoRequest(
            registrationDate = LocalDate.of(2025, 1, 1),
            expectedArrivalDate = LocalDate.of(2025, 1, 10),
            ordererName = "발주처 A",
            customerName = "고객사 B",
            fabricTypeName = "PET",
            width = 150,
            length = 200.0,
            thickness = 0.3,
            quantity = 500,
            comment = "테스트용 원단입니다"
        )
    }
}
