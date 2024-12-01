package aston.repository;

import aston.exception.InternalServerException;
import aston.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbc;
    private final RowMapper<User> mapper;

    public Optional<User> findOne(Long id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try {
            User result = jdbc.queryForObject(query, mapper);
            return Optional.ofNullable(result);
        } catch (EmptyResultDataAccessException ignored) {
            return Optional.empty();
        }
    }

    public Collection<User> findMany() {
        String query = "SELECT * FROM users";
        return jdbc.query(query, mapper);
    }

    public User create(User user) {
        String query = "INSERT INTO users (name) VALUES (?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            return ps;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (Objects.nonNull(keys)) {
            Long key = (Long) keys.get("id");
            user.setId(key);
            return user;
        } else {
            throw new InternalServerException("Не удалось сохранить данные");
        }
    }

    public User update(User user) {
        String query = "UPDATE users SET name = ? WHERE id = ?";
        int rowsUpdated = jdbc.update(query, user.getName(), user.getId());
        if (rowsUpdated == 0) {
            throw new InternalServerException("Не удалось обновить данные");
        }
        return user;
    }
}
