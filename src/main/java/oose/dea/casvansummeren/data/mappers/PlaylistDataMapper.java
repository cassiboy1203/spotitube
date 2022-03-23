package oose.dea.casvansummeren.data.mappers;

import oose.dea.casvansummeren.DTO.PlaylistDTO;
import oose.dea.casvansummeren.exceptions.DatabaseException;

import javax.enterprise.inject.Default;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistDataMapper implements IPlaylistDataMapper{

    @Override
    public List<PlaylistDTO> getPlaylists(ResultSet result, int user) throws SQLException {
        var playlists = new ArrayList<PlaylistDTO>();

        while (result.next()){
            var playlist = new PlaylistDTO();
            playlist.setId(result.getInt("id"));
            playlist.setName(result.getString("name"));
            if (result.getInt("owner") == user)
                playlist.setOwner(true);
            playlist.setLength(result.getInt("duration"));

            playlists.add(playlist);
        }

        return playlists;
    }

    @Override
    public int getPlaylistOwner(ResultSet result) throws SQLException {
        if (result.next()){
            return result.getInt("owner");
        }
        throw new DatabaseException();
    }
}
