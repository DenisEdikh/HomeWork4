package aston.service;

import aston.dto.user.NewUserDto;
import aston.dto.user.UpdateUserDto;
import aston.dto.user.UserDto;
import aston.exception.NotFoundException;
import aston.mapper.UserMapper;
import aston.model.User;
import aston.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Collection<User> getAllUsers() {
        return userRepository.findMany();
    }

    public User getUserById(Long id) {
        return userRepository.findOne(id)
                .orElseThrow(() -> {
                    log.warn("Пользователь c id = {} не найден", id);
                    return new NotFoundException(String.format("Пользователь с id = %d не найден", id));
                });
    }

    @Transactional
    public UserDto create(NewUserDto dto) {
        return userMapper.toUserDto(userRepository.create(userMapper.toUser(dto)));
    }

    @Transactional
    public UserDto update(UpdateUserDto dto) {
        log.debug("Начата проверка наличия пользователя c id = {} в БД в методе update", dto.getId());
        getUserById(dto.getId());

        return userMapper.toUserDto(userRepository.update(userMapper.toUser(dto)));
    }
}