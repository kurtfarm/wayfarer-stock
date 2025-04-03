package wayfarer_stock.fabric.domain.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import wayfarer_stock.fabric.application.dto.FabricCodeRequest
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
        val registrationDate: LocalDate = LocalDate.of(2025, 3, 26)
        val fabricTypeCode: String = "01"
        val width: Long = 123
        val length: Double = 1.0

        val fabricCodeRequest = FabricCodeRequest.of(registrationDate, fabricTypeCode, width, length)

        // when
        val result = fabricCodeService.createFabricCode(fabricCodeRequest)

        // then
        assertThat(result).isEqualTo("25011230001")
    }
}
