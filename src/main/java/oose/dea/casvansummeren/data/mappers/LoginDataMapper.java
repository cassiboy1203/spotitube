package oose.dea.casvansummeren.data.mappers;

import oose.dea.casvansummeren.DTO.UserDTO;
import oose.dea.casvansummeren.exceptions.DatabaseException;

import javax.enterprise.inject.Default;
import java.sql.ResultSet;
import java.sql.SQLException;

@Default
public class LoginDataMapper implements ILoginDataMapper{
    @Override
    public UserDTO getUserInfo(ResultSet result) throws SQLException {
        if (result.next()){
            var userInfo = new UserDTO();
            userInfo.setId(result.getInt("userid"));
            userInfo.setName(result.getString("username"));
            userInfo.setPassword(result.getString("password"));
            return userInfo;
        }
        return null;
    }
}
