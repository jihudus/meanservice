package jihudus.meanservice.controller;

import jihudus.meanservice.advice.InvalidTimeIntervalException;
import jihudus.meanservice.service.MeanCalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class MeanReceiptControllerTest {

    @InjectMocks
    private MeanReceiptController meanReceiptController;
    @Mock
    private MeanCalculatorService meanCalculatorService;

    @Test
    void getMeanReceiptValue_Should_Throw_InvalidTimeIntervalException() {
        UUID uuid = UUID.randomUUID();
        LocalDate fromDate = LocalDate.of(2024,2,1);
        LocalDate tillDate = LocalDate.of(2024,1,1);
        Assertions.assertThrows(InvalidTimeIntervalException.class,
                () -> meanReceiptController.getMeanReceiptValue(uuid, fromDate, tillDate));
    }

    @Test
    void getMeanReceiptValue_Should_Return_Value() {
        Mockito
                .when(meanCalculatorService
                    .calculateMeanValue(
                            Mockito.any(UUID.class),
                            Mockito.any(LocalDate.class),
                            Mockito.any(LocalDate.class)))
                .thenReturn(BigDecimal.ZERO);
        Assertions.assertEquals(
                HttpStatus.OK,
                meanReceiptController.getMeanReceiptValue(UUID.randomUUID(), LocalDate.now(), LocalDate.now())
                        .getStatusCode()
        );
    }


}