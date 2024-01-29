package jihudus.meanservice.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class MeanReceiptControllerAdvice {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleException(MethodArgumentTypeMismatchException ex) {
        String message = ex.getMessage();
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(InvalidTimeIntervalException.class)
    public ResponseEntity<String> handleException(InvalidTimeIntervalException ex) {
        String message = ex.getMessage();
        return ResponseEntity.badRequest().body(message);
    }
}
