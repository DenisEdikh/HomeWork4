package aston.dto.film;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FilmDto {
    private Long id;
    private String name;
    private Integer likes;
}
