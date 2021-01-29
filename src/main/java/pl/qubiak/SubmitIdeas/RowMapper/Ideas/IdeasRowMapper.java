package pl.qubiak.SubmitIdeas.RowMapper.Ideas;

import org.springframework.jdbc.core.RowMapper;
import pl.qubiak.SubmitIdeas.Model.Ideas.IdeasModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IdeasRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {

        IdeasModel ideasModel = new IdeasModel();
        ideasModel.setId(resultSet.getInt("id"));
        ideasModel.setIdea(resultSet.getString("idea"));
        ideasModel.setAuthor(resultSet.getString("author"));
        ideasModel.setAccepted(resultSet.getBoolean("is_accepted"));

        return ideasModel;
    }
}
