package dgs.example.demo.shared.exception;

import java.util.Optional;

public class UnauthorizedException extends RuntimeException {

    private final int errorCode;
    private final Optional<String> debugMessage;

    public UnauthorizedException(String message, Optional<String> debugMessage) {
        super(message);
        this.errorCode = 401;
        this.debugMessage = debugMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public Optional<String> getDebugMessage() {
        return debugMessage;
    }
}
