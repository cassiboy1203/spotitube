package oose.dea.casvansummeren.business.services;

import oose.dea.casvansummeren.api.interfaces.ILoginService;
import oose.dea.casvansummeren.api.DTO.LoginResponseDTO;

import javax.enterprise.inject.Default;

@Default
public class LoginService extends SpotitubeService implements ILoginService {
    @Override
    public LoginResponseDTO generateResponse(String user) {
        var token = authService.generateToken();
        authService.saveToken(token, user);
        return new LoginResponseDTO(token, user);
    }

    @Override
    public boolean checkLogin(String user, String password) {
        return user.equals("admin") && password.equals("admin");
    }
}
