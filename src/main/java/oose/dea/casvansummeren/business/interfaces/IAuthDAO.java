package oose.dea.casvansummeren.business.interfaces;

public interface IAuthDAO {
    int getUserId(String token);
    void saveAuth(String token, String user);
}
