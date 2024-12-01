package aston.controller;

import aston.dto.film.FilmDto;
import aston.dto.film.NewFilmDto;
import aston.dto.film.UpdateFilmDto;
import aston.service.FilmService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/films")
@Slf4j
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    @GetMapping("/{id}")
    public FilmDto getFilmById(@PathVariable(value = "id") Long id) {
        final FilmDto film = filmService.getFilmById(id);
        return film;
    }

    @PostMapping
    public FilmDto create(@Valid @RequestBody NewFilmDto film) {
        final FilmDto savedFilm = filmService.create(film);
        return savedFilm;
    }

    @PutMapping
    public FilmDto update(@Valid @RequestBody UpdateFilmDto dto) {
        final FilmDto savedFilm = filmService.update(dto);
        return savedFilm;
    }

    @PutMapping("/{id}/like/{userId}")
    public void addLike(@PathVariable(value = "id") Long id,
                        @PathVariable(value = "userId") Long userId) {
        filmService.addLike(id, userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void deleteLike(@PathVariable(value = "id") Long id,
                           @PathVariable(value = "userId") Long userId) {
        filmService.deleteLike(id, userId);
    }

    @GetMapping("/popular")
    public List<FilmDto> getPopularFilms() {
        final List<FilmDto> popularFilms = filmService.getPopularFilms();
        return popularFilms;
    }
}
