package jihudus.meanservice.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@RestController
@RequestMapping
public class MeanReceiptController {

    @GetMapping("/{uuid}")
    @Parameter(name = "uuid", required = true, description = "Client id")
    @Parameter(name = "fromDate", required = true, description = "Start of the date interval")
    @Parameter(name = "tillDate", description = "Finish of the date interval")
    public BigDecimal getMeanReceiptValue(@PathVariable UUID uuid,
                                          @RequestParam LocalDate fromDate,
                                          @RequestParam LocalDate tillDate) {
        if (tillDate == null) tillDate = LocalDate.now();
        long interval = ChronoUnit.DAYS.between(tillDate, fromDate);
        return new BigDecimal(interval);
    }
}
