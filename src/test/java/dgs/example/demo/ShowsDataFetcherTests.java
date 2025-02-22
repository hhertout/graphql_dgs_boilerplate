package dgs.example.demo;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest;
import com.netflix.graphql.dgs.test.EnableDgsTest;
import dgs.example.demo.application.context.GraphqlContext;
import dgs.example.demo.application.resolver.ShowsDataFetcher;
import dgs.example.demo.codegen.client.ShowsGraphQLQuery;
import dgs.example.demo.codegen.client.ShowsProjectionRoot;
import dgs.example.demo.infra.entity.Show;
import dgs.example.demo.infra.repository.ShowRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(classes = {ShowsDataFetcher.class, TestDgsCustomContextBuilder.class})
@EnableDgsTest
public class ShowsDataFetcherTests {

    @Autowired
    DgsQueryExecutor dgsQueryExecutor;

    @MockitoBean
    ShowRepository showsRepository;

    @MockitoBean
    GraphqlContext graphqlContext;

    @BeforeEach
    public void before() {
        graphqlContext = new GraphqlContext(Optional.of("azertyuiop"));

        Show mockShow = new Show();
        mockShow.setTitle("mock title");
        mockShow.setReleaseYear(2024);

        Mockito.when(showsRepository.findAll()).thenAnswer(_ -> List.of(mockShow));
    }

    @Test
    public void showsWithQueryApi() {
        GraphQLQueryRequest graphQLQueryRequest = new GraphQLQueryRequest(
                new ShowsGraphQLQuery.Builder().build(),
                new ShowsProjectionRoot().title()
        );

        List<String> titles = dgsQueryExecutor.executeAndExtractJsonPath(graphQLQueryRequest.serialize(), "data.shows[*].title");
        assertThat(titles).containsExactly("mock title");
    }
}