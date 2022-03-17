package oose.dea.casvansummeren.business.services;

import oose.dea.casvansummeren.api.interfaces.IAuthService;
import oose.dea.casvansummeren.business.interfaces.IAuthDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.UUID;

@Default
public class AuthService implements IAuthService {

    private IAuthDAO authDAO;

    @Inject
    public void setAuthDAO(IAuthDAO authDAO) {
        this.authDAO = authDAO;
    }

    @Override
    public String generateToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void saveToken(String token, int user) {
        authDAO.saveAuth(token, user);
    }

    @Override
    public int getUser(String token) {
        return authDAO.getUserId(token);
    }
}
