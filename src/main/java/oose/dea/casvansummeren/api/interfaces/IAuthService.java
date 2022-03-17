package oose.dea.casvansummeren.api.interfaces;

public interface IAuthService {
    String generateToken();
    void saveToken(String token, int user);
    int getUser(String token);
}
