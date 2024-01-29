package jihudus.meanservice.advice;

public class InvalidTimeIntervalException extends RuntimeException {

    public InvalidTimeIntervalException(String message) {
        super(message);
    }

    public InvalidTimeIntervalException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTimeIntervalException(Throwable cause) {
        super(cause);
    }
}
