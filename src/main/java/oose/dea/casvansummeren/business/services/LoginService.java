package oose.dea.casvansummeren.business.services;

import oose.dea.casvansummeren.api.interfaces.ILoginService;
import oose.dea.casvansummeren.DTO.LoginResponseDTO;
import oose.dea.casvansummeren.business.interfaces.ILoginDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class LoginService extends SpotitubeService implements ILoginService {

    private ILoginDAO loginDAO;

    @Inject
    public void setLoginDAO(ILoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @Override
    public LoginResponseDTO generateResponse(String user) {
        var token = authService.generateToken();
        authService.saveToken(token, 1);
        return new LoginResponseDTO(token, user);
    }

    @Override
    public boolean checkLogin(String user, String password) {
        var userInfo = loginDAO.getUser(user);

        return userInfo.getPassword().equals(password);
    }
}
