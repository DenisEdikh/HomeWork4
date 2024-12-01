package aston.service;

import aston.dto.director.DirectorDto;
import aston.dto.director.NewDirectorDto;
import aston.dto.director.UpdateDirectorDto;
import aston.exception.ConditionsNotMetException;
import aston.exception.NotFoundException;
import aston.mapper.DirectorMapper;
import aston.model.Director;
import aston.model.Film;
import aston.repository.DirectorRepository;
import aston.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DirectorService {
    private final DirectorRepository directorRepository;
    private final FilmRepository filmRepository;
    private final DirectorMapper directorMapper;

    @Transactional
    public DirectorDto create(NewDirectorDto dto) {
        final List<Film> films = filmRepository.findByIdIn(dto.getFilms());
        if (films.size() != dto.getFilms().size()) {
            throw new ConditionsNotMetException("Films size does not match");
        }
        return directorMapper.toDirectorDto(directorRepository.save(directorMapper.toDirector(dto, films)));
    }

    @Transactional
    public DirectorDto update(UpdateDirectorDto updateDto) {
        Director storedDir = directorRepository.findById(updateDto.getId()).orElseThrow(() ->
                new NotFoundException(String.format("Режиссер с id = %d не найден", updateDto.getId()))
        );
        final List<Film> films = filmRepository.findByIdIn(updateDto.getFilms());
        if (films.size() != updateDto.getFilms().size()) {
            throw new ConditionsNotMetException("Films size does not match");
        }
        Optional.ofNullable(updateDto.getName()).ifPresent(storedDir::setName);
        storedDir.setFilms(new HashSet<>(films));
        return directorMapper.toDirectorDto(directorRepository.save(storedDir));
    }

    public DirectorDto getDirectorById(Long id) {
        Director storedDir = directorRepository.findDirectorById(id).orElseThrow(() ->
                new NotFoundException(String.format("Режиссер с id = %d не найден", id))
        );
        return directorMapper.toDirectorDto(storedDir);
    }
}
