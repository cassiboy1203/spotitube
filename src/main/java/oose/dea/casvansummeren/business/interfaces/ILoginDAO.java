package oose.dea.casvansummeren.business.interfaces;


import oose.dea.casvansummeren.DTO.UserDTO;

public interface ILoginDAO {
    UserDTO getUser(String user);
}
