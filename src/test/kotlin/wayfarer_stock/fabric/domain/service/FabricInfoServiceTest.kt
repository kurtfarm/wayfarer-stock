package wayfarer_stock.fabric.domain.service

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import wayfarer_stock.fabric.application.dto.FabricInfoCreateRequest
import wayfarer_stock.fabric.controller.dto.request.FabricInfoRequest
import wayfarer_stock.fabric.domain.entity.FabricInfo
import wayfarer_stock.fabric.domain.repository.FabricInfoRepository
import java.time.LocalDate
import java.util.*

@ExtendWith(MockitoExtension::class)
class FabricInfoServiceTest {
    @Mock
    private lateinit var fabricInfoRepository: FabricInfoRepository

    @InjectMocks
    private lateinit var fabricInfoService: FabricInfoService

    @InjectMocks
    private lateinit var readFabricInfoService: ReadFabricInfoService

    @Test
    fun `원단 정보를 생성한다`() {
        // given
        val request = createFakeRequest()

        // when
        fabricInfoService.createFabricInfo(request)

        // then
        verify(fabricInfoRepository).save(argThat { entity ->
            entity.registrationDate == request.registrationDate &&
                    entity.expectedArrivalDate == request.expectedArrivalDate &&
                    entity.ordererId == request.ordererId &&
                    entity.customerId == request.customerId &&
                    entity.codeId == request.codeId &&
                    entity.fabric.fabricType.description == request.fabricTypeName &&
                    entity.fabric.width == request.width &&
                    entity.fabric.length == request.length &&
                    entity.fabric.thickness == request.thickness &&
                    entity.fabric.quantity == request.quantity &&
                    entity.comment == request.comment
        })
    }

    @Test
    fun `원단 정보를 수정한다`() {
        // given
        val request = createFakeRequest()
        val mockEntity = mock(FabricInfo::class.java)

        // when
        fabricInfoService.updateFabricInfo(mockEntity, request)

        // then
        verify(mockEntity).update(request)
    }

    @Test
    fun `존재하지 않는 원단 정보를 불러오면 예외가 발생한다`() {
        // given
        val id = 999L
        val request = createFakeRequest()

        `when`(fabricInfoRepository.findById(id)).thenReturn(Optional.empty())

        // when & then
        assertThatThrownBy {
            readFabricInfoService.getFabricInfo(id)
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `원단 정보를 삭제한다`() {
        // given
        val id = 1L

        // when
        fabricInfoService.deleteFabricInfo(id)

        // then
        verify(fabricInfoRepository).deleteById(id)
    }


    private fun createFakeRequest(): FabricInfoCreateRequest {
        return FabricInfoCreateRequest(
            registrationDate = LocalDate.of(2025, 1, 1),
            expectedArrivalDate = LocalDate.of(2025, 1, 10),
            ordererId = 1L,
            customerId = 2L,
            codeId = 3L,
            fabricTypeName = "PET",
            width = 150,
            length = 200.0,
            thickness = 0.3,
            quantity = 500,
            comment = "테스트용 원단입니다"
        )
    }
}
