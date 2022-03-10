package oose.dea.casvansummeren.api.interfaces;

import oose.dea.casvansummeren.api.DTO.LoginResponseDTO;

public interface ILoginService{
    LoginResponseDTO generateResponse(String user);
    boolean checkLogin(String user, String password);
}
