package aston.mapper;

import aston.dto.film.FilmDto;
import aston.dto.film.NewFilmDto;
import aston.model.Film;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class FilmMapper {
    public Film toFilm(NewFilmDto dto) {
        Film film = new Film();
        film.setName(dto.getName());
        film.setDuration(dto.getDuration());
        return film;
    }
    public FilmDto toFilmDto(Film film) {
        FilmDto filmDto = new FilmDto();
        filmDto.setId(film.getId());
        filmDto.setName(film.getName());
        filmDto.setLikes(film.getUsers().size());
        return filmDto;
    }

    public List<FilmDto> toFilmDto(Set<Film> film) {
        return film.stream().map(this::toFilmDto).toList();
    }
}
