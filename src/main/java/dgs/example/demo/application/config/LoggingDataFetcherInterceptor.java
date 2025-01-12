package dgs.example.demo.application.config;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class LoggingDataFetcherInterceptor implements WebGraphQlInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingDataFetcherInterceptor.class);

    @NotNull
    @Override
    public Mono<WebGraphQlResponse> intercept(@NotNull WebGraphQlRequest request, Chain chain) {
        long startTime = System.currentTimeMillis();

        Mono<WebGraphQlResponse> response = chain.next(request);

        long duration = System.currentTimeMillis() - startTime;
        logger.info("GraphQL query completed in {} ms", duration);

        return response;
    }
}
