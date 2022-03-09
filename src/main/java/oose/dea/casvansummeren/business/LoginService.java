package oose.dea.casvansummeren.business;

import oose.dea.casvansummeren.api.ILoginService;
import oose.dea.casvansummeren.api.DTO.LoginResponseDTO;

import javax.enterprise.inject.Default;
import java.util.UUID;

@Default
public class LoginService implements ILoginService {
    @Override
    public LoginResponseDTO generateResponse(String user) {
        var token = "1234-1234-1234";//UUID.randomUUID().toString();

        return new LoginResponseDTO(token, user);
    }

    @Override
    public boolean checkLogin(String user, String password) {
        return user.equals("admin") && password.equals("admin");
    }
}
