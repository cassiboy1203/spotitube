package oose.dea.casvansummeren.business.interfaces;


import oose.dea.casvansummeren.business.UserDTO;

public interface ILoginDAO {
    UserDTO getUser(String user);
}
