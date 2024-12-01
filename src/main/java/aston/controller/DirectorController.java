package aston.controller;

import aston.dto.director.DirectorDto;
import aston.dto.director.NewDirectorDto;
import aston.dto.director.UpdateDirectorDto;
import aston.service.DirectorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/directors")
@Slf4j
@RequiredArgsConstructor
public class DirectorController {
    private final DirectorService directorService;

    @GetMapping("{id}")
    public DirectorDto getDirectorById(@PathVariable(value = "id") Long id) {
        final DirectorDto director = directorService.getDirectorById(id);
        return director;
    }

    @PostMapping
    public DirectorDto createDirector(@Valid @RequestBody NewDirectorDto director) {
        final DirectorDto savedDirector = directorService.create(director);
        return savedDirector;
    }

    @PutMapping
    public DirectorDto updateDirector(@Valid @RequestBody UpdateDirectorDto newDirector) {
        final DirectorDto savedDirector = directorService.update(newDirector);
        return savedDirector;
    }
}
