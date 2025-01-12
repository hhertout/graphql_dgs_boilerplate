package dgs.example.demo.shared.exception;

public class ForbiddenException extends RuntimeException {
    private final int errorCode;

    public ForbiddenException(String message) {
        super(message);
        this.errorCode = 403;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
