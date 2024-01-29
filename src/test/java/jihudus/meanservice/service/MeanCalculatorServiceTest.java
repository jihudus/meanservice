package jihudus.meanservice.service;

import jihudus.meanservice.data.Client;
import jihudus.meanservice.data.Receipt;
import jihudus.meanservice.data.ReceiptRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class MeanCalculatorServiceTest {

    @Mock
    private ReceiptRepository receiptRepository;
    @InjectMocks
    private MeanCalculatorService meanCalculatorService;

    @Test
    void calculateMeanValue_Should_Return_Mean_Value() {
        Client testClient = new Client(UUID.randomUUID(), "Test client");
        Mockito
                .when(
                    receiptRepository.findByClient_IdAndDateBetween(Mockito.any(UUID.class),
                    Mockito.any(LocalDate.class), Mockito.any(LocalDate.class)))
                .thenReturn(Set.of(
                    new Receipt(UUID.randomUUID(), testClient, LocalDate.now(), new BigDecimal("1000.02")),
                    new Receipt(UUID.randomUUID(), testClient, LocalDate.now(), new BigDecimal("2000.03")),
                    new Receipt(UUID.randomUUID(), testClient, LocalDate.now(), new BigDecimal("3000.04")),
                    new Receipt(UUID.randomUUID(), testClient, LocalDate.now(), new BigDecimal("4000.05"))
                )
        );
        Assertions.assertEquals(new BigDecimal("2500.04"),
                meanCalculatorService.calculateMeanValue(UUID.randomUUID(), LocalDate.now(), LocalDate.now()));
    }

    @Test
    void calculateMeanValue_Should_Return_Zero() {
        Mockito
                .when(
                        receiptRepository.findByClient_IdAndDateBetween(Mockito.any(UUID.class),
                                Mockito.any(LocalDate.class), Mockito.any(LocalDate.class)))
                .thenReturn(Collections.emptySet());
        Assertions.assertEquals(BigDecimal.ZERO,
                meanCalculatorService.calculateMeanValue(UUID.randomUUID(), LocalDate.now(), LocalDate.now()));
    }
}