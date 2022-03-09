package oose.dea.casvansummeren.api;

import oose.dea.casvansummeren.api.DTO.LoginResponseDTO;

public interface ILoginService {
    LoginResponseDTO generateResponse(String user);
    boolean checkLogin(String user, String password);
}
