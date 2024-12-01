package aston.repository;

import aston.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    List<Film> findByIdIn(Set<Long> Ids);

    @Query("""
        select f from Film f
        order by size(f.users) desc
       """)
    List<Film> getPopFilms();
}
