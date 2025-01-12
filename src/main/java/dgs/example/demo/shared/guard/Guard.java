package dgs.example.demo.shared.guard;

import dgs.example.demo.shared.exception.UnauthorizedException;

import java.util.Arrays;
import java.util.Optional;

public class Guard {
    public static void checkAuthorization(Optional<String> token, Role... roles) throws UnauthorizedException {
        if (!tokenIsValid(token)) throw new UnauthorizedException("Unauthorized");
        Role userRole = getRole();
        if (!Arrays.asList(roles).contains(userRole)) throw new UnauthorizedException("Unauthorized");
    }

    private static boolean tokenIsValid(Optional<String> token) {
        // TODO
        return true;
    }

    private static Role getRole() {
        // TODO
        return Role.Viewer;
    }
}
