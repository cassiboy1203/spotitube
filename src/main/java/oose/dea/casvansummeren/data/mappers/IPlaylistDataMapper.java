package oose.dea.casvansummeren.data.mappers;

import oose.dea.casvansummeren.DTO.PlaylistDTO;
import oose.dea.casvansummeren.DTO.TrackDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IPlaylistDataMapper {
    List<PlaylistDTO> getPlaylists(ResultSet result, int user) throws SQLException;
    int getPlaylistOwner(ResultSet result) throws SQLException;
}
