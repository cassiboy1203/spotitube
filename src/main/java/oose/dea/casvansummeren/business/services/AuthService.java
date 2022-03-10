package oose.dea.casvansummeren.business.services;

import oose.dea.casvansummeren.api.interfaces.IAuthService;
import oose.dea.casvansummeren.business.interfaces.IAuthDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class AuthService implements IAuthService {

    private IAuthDAO authDAO;

    @Inject
    public void setAuthDAO(IAuthDAO authDAO) {
        this.authDAO = authDAO;
    }

    @Override
    public String generateToken() {
        //return UUID.randomUUID().toString();
        return "cbcc6ed9-eb6b-439c-b0c0-8d5b9c676e0e";
    }

    @Override
    public void saveToken(String token, String username) {
        authDAO.saveAuth(token, username);
    }

    @Override
    public int getUser(String token) {
        return authDAO.getUserId(token);
    }
}
