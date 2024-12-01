package aston.dto.director;

import aston.dto.film.FilmDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DirectorDto {
    private Long id;
    private String name;
    private List<FilmDto> films;
}
