package dgs.example.demo.shared.context;

import com.netflix.graphql.dgs.context.DgsCustomContextBuilderWithRequest;
import dgs.example.demo.shared.exception.ForbiddenException;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.Optional;

@Component
public class GraphqlContextBuilder implements DgsCustomContextBuilderWithRequest<GraphqlContext> {

    @Override
    public GraphqlContext build(@Nullable Map<String, ?> extensions, @Nullable HttpHeaders headers, @Nullable WebRequest webRequest) {
        String API_KEY = System.getenv("API_KEY");

        if (headers != null) {
            String headerApiKey = headers.getFirst("x-api-key");

            System.out.println("AUTH HEADER = " + headers.getFirst("authorization"));

            if (headerApiKey == null || !headerApiKey.equals(API_KEY)) {
                throw new ForbiddenException("API KEY IS NOT VALID");
            }

            String authHeader = headers.getFirst("authorization");
            return new GraphqlContext(Optional.ofNullable(authHeader));
        } else {
            throw new ForbiddenException("INVALID OPERATION");
        }
    }
}
