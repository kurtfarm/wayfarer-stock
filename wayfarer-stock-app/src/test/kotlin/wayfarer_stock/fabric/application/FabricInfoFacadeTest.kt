package wayfarer_stock.fabric.application

import com.dkprint.app.fabric.application.FabricInfoFacade
import com.dkprint.app.fabric.domain.entity.Fabric
import com.dkprint.app.fabric.domain.entity.FabricInfo
import com.dkprint.app.fabric.domain.entity.FabricType
import com.dkprint.app.fabric.domain.service.EditFabricInfoService
import com.dkprint.app.fabric.domain.service.FabricCodeService
import com.dkprint.app.fabric.domain.service.ReadFabricInfoService
import com.dkprint.app.fabric.domain.service.RegisterFabricInfoService
import com.dkprint.app.fabric.dto.response.FabricInfoListResponse
import com.dkprint.app.fabric.dto.response.FabricInfoResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class FabricInfoFacadeListTest {

    @Mock
    lateinit var registerFabricInfoService: RegisterFabricInfoService

    @Mock
    lateinit var editFabricInfoService: EditFabricInfoService

    @Mock
    lateinit var readFabricInfoService: ReadFabricInfoService

    @Mock
    lateinit var fabricCodeService: FabricCodeService

    @InjectMocks
    lateinit var fabricInfoFacade: FabricInfoFacade

    @Test
    fun `원단 상세 정보를 조회한다`() {
        // given
        val id = 1L
        val dummy = dummyFabricInfo()

        given(readFabricInfoService.getFabricInfo(id)).willReturn(dummy)

        // when
        val result = fabricInfoFacade.getDetailedFabricInfo(id)

        // then
        assertThat(result).isInstanceOf(FabricInfoResponse::class.java)
        assertThat(result.ordererName).isEqualTo("발주처 1")     // 내부적으로 고정된 반환값
        assertThat(result.customerName).isEqualTo("고객사 1")
        assertThat(result.fabricCode).isEqualTo("25010990022")
        assertThat(result.width).isEqualTo(dummy.fabric.width)
        assertThat(result.quantity).isEqualTo(dummy.fabric.quantity)
    }

    @Test
    fun `전체 원단 리스트를 페이징 조회한다`() {
        // given
        val pageRequest = PageRequest.of(0, 10)
        val page = PageImpl(listOf(dummyFabricInfo()))
        given(readFabricInfoService.getList(pageRequest)).willReturn(page)

        // when
        val result = fabricInfoFacade.getFabricInfoList(0, 10)

        // then
        assertThat(result.content).hasSize(1)
        assertThat(result.content[0]).isInstanceOf(FabricInfoListResponse::class.java)
    }

    @Test
    fun `발주처 기준으로 원단 리스트를 조회한다`() {
        // given
        val startDate = LocalDate.of(2025, 4, 1)
        val endDate = LocalDate.of(2025, 4, 10)
        val ordererId = 1L
        val pageRequest = PageRequest.of(0, 10)
        val page = PageImpl(listOf(dummyFabricInfo()))
        given(readFabricInfoService.getListByOrderer(startDate, endDate, ordererId, pageRequest)).willReturn(page)

        // when
        val result = fabricInfoFacade.getFabricInfoListByOrderer(0, 10, startDate, endDate, "발주처 1")

        // then
        assertThat(result.content).hasSize(1)
        assertThat(result.content[0].ordererName).isEqualTo("발주처 1")
    }

    @Test
    fun `원단 종류 기준으로 원단 리스트를 조회한다`() {
        // given
        val startDate = LocalDate.of(2025, 4, 1)
        val endDate = LocalDate.of(2025, 4, 10)
        val pageRequest = PageRequest.of(0, 10)
        val page = PageImpl(listOf(dummyFabricInfo()))
        given(readFabricInfoService.getListByFabricType(startDate, endDate, "PET", pageRequest)).willReturn(page)

        // when
        val result = fabricInfoFacade.getFabricInfoListByType(0, 10, startDate, endDate, "PET")

        // then
        assertThat(result.content).hasSize(1)
        assertThat(result.content[0]).isInstanceOf(FabricInfoListResponse::class.java)
    }

    private fun dummyFabricInfo(): FabricInfo {
        return FabricInfo(
            registrationDate = LocalDate.of(2025, 4, 1),
            expectedArrivalDate = LocalDate.of(2025, 4, 10),
            ordererId = 1L,
            customerId = 2L,
            codeId = 3L,
            fabric = Fabric(
                fabricType = FabricType.PET,
                fabricTypeDetail = "PET",
                width = 100,
                length = 200.0,
                thickness = 0.5,
                quantity = 1000
            ),
            comment = "테스트 원단"
        )
    }
}
