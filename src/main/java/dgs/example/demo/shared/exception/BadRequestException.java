package dgs.example.demo.shared.exception;

public class BadRequestException extends RuntimeException {

    private final int errorCode;

    public BadRequestException(String message) {
        super(message);
        this.errorCode = 400;
    }

    public int getErrorCode() {
        return errorCode;
    }
}

