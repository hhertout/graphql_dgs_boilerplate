package dgs.example.demo.shared.exception;

import java.util.Optional;

public class BadRequestException extends RuntimeException {

    private final int errorCode;
    private final Optional<String> debugMessage;

    public BadRequestException(String message, Optional<String> debugMessage) {
        super(message);
        this.errorCode = 400;
        this.debugMessage = debugMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public Optional<String> getDebugMessage() {
        return debugMessage;
    }

}

