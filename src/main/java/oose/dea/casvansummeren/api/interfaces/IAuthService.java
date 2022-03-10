package oose.dea.casvansummeren.api.interfaces;

public interface IAuthService {
    String generateToken();
    void saveToken(String token, String username);
    int getUser(String token);
}
