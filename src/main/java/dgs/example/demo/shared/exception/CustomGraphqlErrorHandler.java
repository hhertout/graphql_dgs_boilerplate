package dgs.example.demo.shared.exception;

import com.netflix.graphql.dgs.exceptions.DefaultDataFetcherExceptionHandler;
import com.netflix.graphql.types.errors.TypedGraphQLError;
import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class CustomGraphqlErrorHandler implements DataFetcherExceptionHandler {

    enum CustomErrorType implements ErrorClassification {
        BAD_REQUEST,
        SERVER_ERROR,
        UNAUTHORIZED
    }

    @Override
    public CompletableFuture<DataFetcherExceptionHandlerResult> handleException(DataFetcherExceptionHandlerParameters handlerParameters) {
        if (handlerParameters.getException() instanceof BadRequestException) {
            Map<String, Object> debugInfo = new HashMap<>();
            debugInfo.put("error", "invalid user entry");

            GraphQLError graphqlError = TypedGraphQLError.newInternalErrorBuilder()
                    .message(handlerParameters.getException().getMessage())
                    .errorType(CustomErrorType.BAD_REQUEST)
                    .debugInfo(debugInfo)
                    .path(handlerParameters.getPath()).build();

            DataFetcherExceptionHandlerResult result = DataFetcherExceptionHandlerResult.newResult()
                    .error(graphqlError)
                    .build();

            return CompletableFuture.completedFuture(result);
        } else if (handlerParameters.getException() instanceof ServerException) {
            Map<String, Object> debugInfo = new HashMap<>();
            debugInfo.put("error", "server error");

            GraphQLError graphqlError = TypedGraphQLError.newInternalErrorBuilder()
                    .message(handlerParameters.getException().getMessage())
                    .errorType(CustomErrorType.SERVER_ERROR)
                    .debugInfo(debugInfo)
                    .path(handlerParameters.getPath()).build();

            DataFetcherExceptionHandlerResult result = DataFetcherExceptionHandlerResult.newResult()
                    .error(graphqlError)
                    .build();

            return CompletableFuture.completedFuture(result);
        } else if (handlerParameters.getException() instanceof UnauthorizedException) {
            Map<String, Object> debugInfo = new HashMap<>();
            debugInfo.put("error", "you are not authorized to see this content");

            GraphQLError graphqlError = TypedGraphQLError.newInternalErrorBuilder()
                    .message(handlerParameters.getException().getMessage())
                    .errorType(CustomErrorType.UNAUTHORIZED)
                    .debugInfo(debugInfo)
                    .path(handlerParameters.getPath()).build();

            DataFetcherExceptionHandlerResult result = DataFetcherExceptionHandlerResult.newResult()
                    .error(graphqlError)
                    .build();

            return CompletableFuture.completedFuture(result);
        } else {
            return new DefaultDataFetcherExceptionHandler().handleException(handlerParameters);
        }
    }
}
