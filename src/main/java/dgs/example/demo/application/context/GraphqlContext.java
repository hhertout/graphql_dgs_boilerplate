package dgs.example.demo.application.context;

import java.util.Optional;

public record GraphqlContext(Optional<String> authorizationHeader) {
}
