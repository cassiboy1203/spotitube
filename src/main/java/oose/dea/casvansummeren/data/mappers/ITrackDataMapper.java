package oose.dea.casvansummeren.data.mappers;

import oose.dea.casvansummeren.DTO.TrackDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ITrackDataMapper {
    List<TrackDTO> getTracks(ResultSet result) throws SQLException;
}
