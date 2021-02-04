package pl.qubiak.SubmitIdeas.RowMapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Ideas implements RowMapper {

    @Override
    public pl.qubiak.SubmitIdeas.Model.Ideas.Ideas mapRow(ResultSet resultSet, int i) throws SQLException {

        pl.qubiak.SubmitIdeas.Model.Ideas.Ideas ideas = new pl.qubiak.SubmitIdeas.Model.Ideas.Ideas();
        ideas.setId(resultSet.getInt("id"));
        ideas.setIdea(resultSet.getString("idea"));
        ideas.setAuthor(resultSet.getString("author"));
        ideas.setAccepted(resultSet.getBoolean("is_accepted"));

        return ideas;
    }
}
