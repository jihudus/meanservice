package jihudus.meanservice.service;

import jihudus.meanservice.data.Receipt;
import jihudus.meanservice.data.ReceiptRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Service
public class MeanCalculatorService {

    private final ReceiptRepository receiptRepository;

    // Result decimal precision
    private final int scale = 2;

    public MeanCalculatorService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public BigDecimal calculateMeanValue(UUID clientId, LocalDate startDate, LocalDate tillDate) {
        Set<Receipt> receiptsWithinInterval = receiptRepository.findByClient_IdAndDateBetween(clientId, startDate, tillDate);
        BigDecimal valueSum = receiptsWithinInterval.stream()
                .map(Receipt::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal size = new BigDecimal(receiptsWithinInterval.size());
        return size.equals(BigDecimal.ZERO) ?
                    BigDecimal.ZERO :
                    valueSum.divide(size, scale, RoundingMode.HALF_UP);
    }
}
