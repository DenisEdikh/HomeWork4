package aston.mapper;

import aston.dto.user.NewUserDto;
import aston.dto.user.UpdateUserDto;
import aston.dto.user.UserDto;
import aston.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User toUser(NewUserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        return user;
    }

    public User toUser(UpdateUserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        return user;
    }

    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        return userDto;
    }
}
