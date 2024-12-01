package aston.service;

import aston.dto.film.FilmDto;
import aston.dto.film.NewFilmDto;
import aston.dto.film.UpdateFilmDto;
import aston.exception.NotFoundException;
import aston.mapper.FilmMapper;
import aston.model.Film;
import aston.model.User;
import aston.repository.FilmRepository;
import aston.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FilmService {
    private final FilmMapper filmMapper;
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;

    @Scheduled(cron = "@daily")
    public List<FilmDto> getPopularFilms() {
        final List<Film> films = filmRepository.getPopFilms();

        return filmMapper.toFilmDto(new HashSet<>(films));
    }

    @Transactional
    public FilmDto create(NewFilmDto dto) {
        return filmMapper.toFilmDto(filmRepository.save(filmMapper.toFilm(dto)));
    }

    @Transactional
    public FilmDto update(UpdateFilmDto dto) {
        final Film storedfilm = filmRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Фильм с id = %d не найден", dto.getId())));
        Optional.ofNullable(dto.getName()).ifPresent(storedfilm::setName);
        Optional.ofNullable(dto.getDuration()).ifPresent(storedfilm::setDuration);

        return filmMapper.toFilmDto(filmRepository.save(storedfilm));
    }

    @Cacheable(value = "films", key = "#id")
    public FilmDto getFilmById(Long id) {
        final Film storedfilm = filmRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Фильм с id = %d не найден", id)));

        return filmMapper.toFilmDto(storedfilm);
    }

    @Transactional
    public void addLike(Long filmId, Long userId) {
        final Film storedfilm = filmRepository.findById(filmId)
                .orElseThrow(() -> new NotFoundException(String.format("Фильм с id = %d не найден", filmId)));
        final User storedUser = userRepository.findOne(userId)
                .orElseThrow(() -> new NotFoundException(String.format("Пользоватеьл с id = %d не найден", userId)));
        storedfilm.addLike(storedUser);
        filmRepository.save(storedfilm);
    }

    @Transactional
    public void deleteLike(Long filmId, Long userId) {
        final Film storedfilm = filmRepository.findById(filmId)
                .orElseThrow(() -> new NotFoundException(String.format("Фильм с id = %d не найден", filmId)));
        final User storedUser = userRepository.findOne(userId)
                .orElseThrow(() -> new NotFoundException(String.format("Пользоватеьл с id = %d не найден", userId)));
        storedfilm.removeLike(storedUser);
        filmRepository.save(storedfilm);
    }
}
