package oose.dea.casvansummeren.business.interfaces;

import oose.dea.casvansummeren.api.DTO.PlaylistDTO;
import oose.dea.casvansummeren.api.DTO.TrackDTO;

import java.util.List;

public interface IPlaylistDAO {
    List<PlaylistDTO> getPlaylists(int user);

    List<TrackDTO> getTracks(int id);

    void addPlaylist(PlaylistDTO playlist, int ownerId);

    void updatePlaylistName(int id, PlaylistDTO playlist);

    int getPlaylistOwner(int id);

    void deletePlaylist(int id);
}
