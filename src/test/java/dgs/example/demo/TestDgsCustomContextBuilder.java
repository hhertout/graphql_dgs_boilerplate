package dgs.example.demo;

import com.netflix.graphql.dgs.context.DgsCustomContextBuilder;
import dgs.example.demo.shared.context.GraphqlContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TestDgsCustomContextBuilder implements DgsCustomContextBuilder<GraphqlContext> {

    @Override
    public GraphqlContext build() {
        return new GraphqlContext(Optional.of("azertyuiop")); // Simule un header d'authentification
    }
}