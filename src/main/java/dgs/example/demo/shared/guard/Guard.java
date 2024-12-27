package dgs.example.demo.shared.guard;

import com.netflix.graphql.dgs.context.DgsContext;
import dgs.example.demo.shared.context.GraphqlContext;
import dgs.example.demo.shared.exception.UnauthorizedException;
import graphql.schema.DataFetchingEnvironment;

import java.util.Arrays;
import java.util.Optional;

public class Guard {
    public static void checkAuthorization(DataFetchingEnvironment dfe, Role... roles) throws UnauthorizedException {
        GraphqlContext ctx = DgsContext.getCustomContext(dfe);
        if (!tokenIsValid(ctx.authorizationHeader())) throw new UnauthorizedException("Unauthorized");
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
