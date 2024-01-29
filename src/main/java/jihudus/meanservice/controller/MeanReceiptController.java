package jihudus.meanservice.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jihudus.meanservice.advice.InvalidTimeIntervalException;
import jihudus.meanservice.service.MeanCalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping
public class MeanReceiptController {

    private final MeanCalculatorService meanCalculatorService;

    public MeanReceiptController(MeanCalculatorService meanCalculatorService) {
        this.meanCalculatorService = meanCalculatorService;
    }

    @GetMapping("/{uuid}")
    @Parameter(name = "uuid", required = true, description = "Client id")
    @Parameter(name = "fromDate", required = true, description = "Start of the date interval")
    @Parameter(name = "tillDate", description = "Finish of the date interval, set today if not present")
    public ResponseEntity<String> getMeanReceiptValue(@PathVariable UUID uuid,
                                              @RequestParam LocalDate fromDate,
                                              @RequestParam(required = false) LocalDate tillDate) {
        if (tillDate == null) tillDate = LocalDate.now();
        if (tillDate.isBefore(fromDate)) {
            throw new InvalidTimeIntervalException("fromDate must be less than tillDate");
        }
        return ResponseEntity.ok(meanCalculatorService.calculateMeanValue(uuid, fromDate, tillDate).toPlainString());
    }
}
