package pl.qubiak.SubmitIdeas.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import pl.qubiak.SubmitIdeas.Model.Users.UsersModel;
import pl.qubiak.SubmitIdeas.RowMapper.Users.UsersRowMapper;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUser(boolean isEnabled, String userName, String role, String userPassword, String email) {
        String sql = "INSERT INTO users (id, is_Enabled, user_name, role, user_password, email) VALUE (NULL, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, new Object[]{isEnabled, userName, role, userPassword, email});
    }

    public UserDetails findByUsername(String s) {
        String sql = "SELECT * FROM users WHERE user_name = ?";
        UsersModel usersModel = jdbcTemplate.queryForObject(sql ,new Object[]{s}, new UsersRowMapper());
        return usersModel;
    }
}
