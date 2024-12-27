package dgs.example.demo.shared.context;

import com.netflix.graphql.dgs.context.DgsCustomContextBuilderWithRequest;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.Optional;

@Component
public class GraphqlContextBuilder implements DgsCustomContextBuilderWithRequest<GraphqlContext> {

    @Override
    public GraphqlContext build(@Nullable Map<String, ?> extensions, @Nullable HttpHeaders headers, @Nullable WebRequest webRequest) {
        if (headers != null) {
            String authHeader = headers.getFirst("authorization");
            return new GraphqlContext(Optional.ofNullable(authHeader));
        }

        return null;
    }
}
