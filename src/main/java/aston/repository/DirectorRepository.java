package aston.repository;

import aston.model.Director;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
    @EntityGraph(attributePaths = {"films"}, type = EntityGraph.EntityGraphType.FETCH)
    Optional<Director> findDirectorById(Long id);
}
