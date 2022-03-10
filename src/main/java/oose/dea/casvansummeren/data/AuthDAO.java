package oose.dea.casvansummeren.data;

import oose.dea.casvansummeren.business.interfaces.IAuthDAO;
import oose.dea.casvansummeren.exceptions.UserNotFoundException;

import javax.enterprise.inject.Default;

@Default
public class AuthDAO implements IAuthDAO {
    @Override
    public int getUserId(String token) {
        try {
            if (token.equals("cbcc6ed9-eb6b-439c-b0c0-8d5b9c676e0e"))
                return 0;
        } catch (NullPointerException e){
            throw new UserNotFoundException();
        }
        throw new UserNotFoundException();
    }

    @Override
    public void saveAuth(String token, String user) {

    }
}
