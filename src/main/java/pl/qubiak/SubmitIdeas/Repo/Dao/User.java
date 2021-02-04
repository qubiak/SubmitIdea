package pl.qubiak.SubmitIdeas.Repo.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.qubiak.SubmitIdeas.Model.Users.AppUser;

import java.util.List;

@Repository
public class User {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public User(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void changeRoleToMod(int id) {
        String sql = "UPDATE app_user SET role = 'ROLE_MOD' WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<AppUser> allUsers() {
        String sql = "SELECT * FROM app_user";
        List<AppUser> allUsers = jdbcTemplate.query(sql, new pl.qubiak.SubmitIdeas.RowMapper.AppUser());
        return allUsers;
    }
}
