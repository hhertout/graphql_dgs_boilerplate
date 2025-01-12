package dgs.example.demo.application.resolver;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.context.DgsContext;
import dgs.example.demo.application.dtos.CreateShowInput;
import dgs.example.demo.application.dtos.DeleteShowResult;
import dgs.example.demo.infra.entity.Show;
import dgs.example.demo.infra.repository.ShowRepository;
import dgs.example.demo.shared.context.GraphqlContext;
import dgs.example.demo.shared.exception.ServerException;
import dgs.example.demo.shared.guard.Guard;
import dgs.example.demo.shared.guard.Role;
import graphql.schema.DataFetchingEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@DgsComponent
public class ShowsResolver {

    private static final Logger log = LoggerFactory.getLogger(ShowsResolver.class);
    @Autowired
    private ShowRepository showRepository;

    @DgsQuery
    public List<Show> shows(@InputArgument String titleFilter, DataFetchingEnvironment dfe) {

        GraphqlContext ctx = DgsContext.getCustomContext(dfe);
        log.debug(String.valueOf(ctx.authorizationHeader()));
        Guard.checkAuthorization(ctx.authorizationHeader(), Role.Admin);

        try {
            if (titleFilter != null && !titleFilter.isBlank()) {
                return showRepository.findByTitleContaining(titleFilter);
            } else {
                return showRepository.findAll();
            }
        } catch (Exception err) {
            throw new ServerException("An error has occurred");
        }
    }

    @DgsMutation
    public Show createShow(@InputArgument CreateShowInput showInput) {
        try {
            Show newShow = new Show();
            newShow.setTitle(showInput.title());
            newShow.setReleaseYear(showInput.releaseYear());

            Show result = showRepository.save(newShow);
            System.out.println(result);

            return result;
        } catch (Exception err) {
            throw new ServerException("An error has occurred");
        }
    }

    @DgsMutation
    public DeleteShowResult deleteShow(@InputArgument String title) {
        try {
            Optional<Show> show = showRepository.findFirstByTitleContaining(title);
            if (show.isPresent()) {
                showRepository.delete(show.get());
                return new DeleteShowResult(true, "Show deleted with success");
            } else {
                return new DeleteShowResult(false, "This show doesn't exist");
            }
        } catch (Exception err) {
            throw new ServerException("An error has occurred");
        }
    }
}
