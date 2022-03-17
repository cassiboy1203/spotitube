package oose.dea.casvansummeren.data;

import oose.dea.casvansummeren.business.UserDTO;
import oose.dea.casvansummeren.business.interfaces.ILoginDAO;
import oose.dea.casvansummeren.exceptions.DatabaseException;

import javax.inject.Inject;
import java.sql.SQLException;

public class LoginDAO implements ILoginDAO {

    private static final String GET_USER_STATEMENT = "SELECT * FROM users WHERE username = ?";

    private IDatabaseService databaseService;

    @Inject
    public void setDatabaseService(IDatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public UserDTO getUser(String user) {
        try (var conn = databaseService.connect();
             var stmt = conn.prepareStatement(GET_USER_STATEMENT)
        ){
            stmt.setString(1,user);
            var result = stmt.executeQuery();

            if (result.next()){
                var userInfo = new UserDTO();
                userInfo.setId(result.getInt("userid"));
                userInfo.setName(result.getString("username"));
                userInfo.setPassword(result.getString("password"));
                return userInfo;
            } else {
                throw new DatabaseException();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
