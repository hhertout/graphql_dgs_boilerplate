package dgs.example.demo.infra.repository;

import dgs.example.demo.infra.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show, Long> {
    Optional<Show> findFirstByTitleContaining(String title);

    List<Show> findByTitleContaining(String title);
}
