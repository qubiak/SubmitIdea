package pl.qubiak.SubmitIdeas.Repo.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class User {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public User(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void changeRoleToMod(int id) {
        String sql = "UPDATE app_user SET role = ROLE_MOD";
        jdbcTemplate.update(sql, id);
    }
}
