package dgs.example.demo.shared.context;

import java.util.Optional;

public record GraphqlContext(Optional<String> authorizationHeader) {
}
