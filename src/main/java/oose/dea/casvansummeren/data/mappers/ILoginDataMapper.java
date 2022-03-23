package oose.dea.casvansummeren.data.mappers;

import oose.dea.casvansummeren.DTO.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ILoginDataMapper {
    UserDTO getUserInfo(ResultSet result) throws SQLException;
}
