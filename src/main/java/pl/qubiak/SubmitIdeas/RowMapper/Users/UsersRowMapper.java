package pl.qubiak.SubmitIdeas.RowMapper.Users;

import org.springframework.jdbc.core.RowMapper;
import pl.qubiak.SubmitIdeas.Model.Users.UsersModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRowMapper implements RowMapper<UsersModel> {

    @Override
    public UsersModel mapRow(ResultSet resultSet, int i) throws SQLException {
        UsersModel usersModel = new UsersModel();
        usersModel.setId(resultSet.getInt("id"));
        usersModel.setEnabled(resultSet.getBoolean("is_Enabled"));
        usersModel.setUserName(resultSet.getString("user_name"));
        usersModel.setRole(resultSet.getString("role"));
        usersModel.setUserPassword(resultSet.getString("user_password"));
        usersModel.setEmail(resultSet.getString("mail"));

        return usersModel;
    }
}
