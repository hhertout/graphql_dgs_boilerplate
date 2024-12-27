package dgs.example.demo.shared.exception;

public class UnauthorizedException extends RuntimeException {
    private final int errorCode;

    public UnauthorizedException(String message) {
        super(message);
        this.errorCode = 401;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
