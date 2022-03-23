package oose.dea.casvansummeren.business.interfaces;

import oose.dea.casvansummeren.DTO.PlaylistDTO;
import oose.dea.casvansummeren.DTO.TrackDTO;

import java.util.List;

public interface IPlaylistDAO{
    List<PlaylistDTO> getPlaylists(int user);

    List<TrackDTO> getTracks(int id);

    void addPlaylist(PlaylistDTO playlist, int ownerId);

    void updatePlaylistName(int id, PlaylistDTO playlist);

    void deletePlaylist(int id);

    void addTrackToPlaylist(int playlistId, TrackDTO track);

    void removeTrackFromPlaylist(int playlistId, int trackId);
}
