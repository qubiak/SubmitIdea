package pl.qubiak.SubmitIdeas.RowMapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppUser implements RowMapper {

    @Override
    public pl.qubiak.SubmitIdeas.Model.Users.AppUser mapRow(ResultSet resultSet, int i) throws SQLException {

        pl.qubiak.SubmitIdeas.Model.Users.AppUser user = new pl.qubiak.SubmitIdeas.Model.Users.AppUser();
        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setMail(resultSet.getString("mail"));
        user.setRole(resultSet.getString("role"));
        user.setEnabled(resultSet.getBoolean("is_enabled"));

        return user;
    }
}
