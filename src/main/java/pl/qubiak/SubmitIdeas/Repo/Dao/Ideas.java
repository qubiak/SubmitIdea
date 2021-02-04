package pl.qubiak.SubmitIdeas.Repo.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Ideas {


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public Ideas(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String addIdeas(String idea, String author) {
        String sql = "INSERT INTO ideas (id, author, idea, is_accepted) VALUES (NULL, ?, ?, 0)";
        jdbcTemplate.update(sql, new Object[]{idea, author});
        return "Ideas: " + idea + " added";
    }

    public List<pl.qubiak.SubmitIdeas.Model.Ideas.Ideas> getAcceptedIdeas() {
        String sql = "SELECT * FROM ideas WHERE is_accepted = 1";
        List<pl.qubiak.SubmitIdeas.Model.Ideas.Ideas> ideas = jdbcTemplate.query(sql, new pl.qubiak.SubmitIdeas.RowMapper.Ideas());
        return ideas;
    }

    public List<pl.qubiak.SubmitIdeas.Model.Ideas.Ideas> getUnacceptedIdeas() {
        String sql = "SELECT * FROM ideas WHERE is_accepted = 0";
        List<pl.qubiak.SubmitIdeas.Model.Ideas.Ideas> ideas = jdbcTemplate.query(sql, new pl.qubiak.SubmitIdeas.RowMapper.Ideas());
        return ideas;
    }

    public String delateIdea(int id) {
        String sql = "DELETE FROM ideas WHERE id = ?";
        jdbcTemplate.update(sql, new Object[]{id});
        return "delete idea with id: " + id;
    }



}
