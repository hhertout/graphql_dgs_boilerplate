package dgs.example.demo.shared.exception;

import java.util.Optional;

public class ServerException extends RuntimeException {

    private final int errorCode;
    private final Optional<String> debugMessage;

    public ServerException(String message, Optional<String> debugMessage) {
        super(message);
        this.errorCode = 500;
        this.debugMessage = debugMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public Optional<String> getDebugMessage() {
        return debugMessage;
    }
}
