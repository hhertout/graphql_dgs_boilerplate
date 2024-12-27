package dgs.example.demo.shared.exception;

public class ServerException extends RuntimeException {
    private final int errorCode;

    public ServerException(String message) {
        super(message);
        this.errorCode = 500;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
