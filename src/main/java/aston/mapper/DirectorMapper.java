package aston.mapper;

import aston.dto.director.DirectorDto;
import aston.dto.director.NewDirectorDto;
import aston.model.Director;
import aston.model.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorMapper {
    private final FilmMapper filmMapper;

    public Director toDirector(NewDirectorDto dto, List<Film> films) {
        Director director = new Director();
        director.setName(dto.getName());
        director.setFilms(new HashSet<>(films));
        return director;
    }

    public DirectorDto toDirectorDto(Director director) {
        DirectorDto directorDto = new DirectorDto();
        directorDto.setId(director.getId());
        directorDto.setName(director.getName());
        directorDto.setFilms(filmMapper.toFilmDto(director.getFilms()));
        return directorDto;
    }
}
