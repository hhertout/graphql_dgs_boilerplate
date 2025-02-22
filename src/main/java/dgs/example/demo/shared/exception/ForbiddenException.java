package dgs.example.demo.shared.exception;

import java.util.Optional;

public class ForbiddenException extends RuntimeException {
    
    private final int errorCode;
    private final Optional<String> debugMessage;


    public ForbiddenException(String message, Optional<String> debugMessage) {
        super(message);
        this.errorCode = 403;
        this.debugMessage = debugMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public Optional<String> getDebugMessage() {
        return debugMessage;
    }
}
