package aston.controller;

import aston.dto.user.NewUserDto;
import aston.dto.user.UpdateUserDto;
import aston.dto.user.UserDto;
import aston.model.User;
import aston.service.UserService;
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

import java.util.Collection;

@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public Collection<User> getAllUsers() {
        final Collection<User> users = userService.getAllUsers();
        return users;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        final User user = userService.getUserById(id);
        return user;
    }

    @PostMapping
    public UserDto create(@Valid @RequestBody NewUserDto user) {
        final UserDto savedUser = userService.create(user);
        return savedUser;
    }

    @PutMapping
    public UserDto update(@Valid @RequestBody UpdateUserDto dto) {
        final UserDto savedUser = userService.update(dto);
        return savedUser;
    }
}
