package oose.dea.casvansummeren.data.DAO;

import oose.dea.casvansummeren.DTO.UserDTO;
import oose.dea.casvansummeren.business.interfaces.ILoginDAO;
import oose.dea.casvansummeren.data.IDatabaseService;
import oose.dea.casvansummeren.data.mappers.ILoginDataMapper;
import oose.dea.casvansummeren.exceptions.DatabaseException;

import javax.inject.Inject;
import java.sql.SQLException;

public class LoginDAO implements ILoginDAO {

    private static final String GET_USER_STATEMENT = "SELECT * FROM users WHERE username = ?";

    private IDatabaseService databaseService;
    private ILoginDataMapper loginDataMapper;

    @Inject
    public void setDatabaseService(IDatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Inject
    public void setLoginDataMapper(ILoginDataMapper loginDataMapper) {
        this.loginDataMapper = loginDataMapper;
    }

    @Override
    public UserDTO getUser(String user) {
        try (var conn = databaseService.connect();
             var stmt = conn.prepareStatement(GET_USER_STATEMENT)
        ){
            stmt.setString(1,user);
            var result = stmt.executeQuery();

            return loginDataMapper.getUserInfo(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
