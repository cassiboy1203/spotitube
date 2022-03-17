package oose.dea.casvansummeren.data;

import oose.dea.casvansummeren.business.interfaces.IAuthDAO;
import oose.dea.casvansummeren.exceptions.InvalidPermissionException;

import javax.enterprise.inject.Default;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Default
@Singleton
public class AuthDAO implements IAuthDAO {

    private Map<String, Integer> auths = new HashMap<>();

    @Override
    public int getUserId(String token) {
        try {
            return auths.get(token);
        } catch (NullPointerException e){
            throw new InvalidPermissionException();
        }
    }

    @Override
    public void saveAuth(String token, int user) {

        auths.values().removeIf(v -> v == user);

        auths.put(token, user);
    }
}
