package oose.dea.casvansummeren.api.interfaces;

import oose.dea.casvansummeren.DTO.PlaylistDTO;
import oose.dea.casvansummeren.DTO.PlaylistsResponseDTO;
import oose.dea.casvansummeren.DTO.TrackDTO;
import oose.dea.casvansummeren.DTO.TrackResponseDTO;

public interface IPlaylistService {
    PlaylistsResponseDTO getPlaylists(String token);

    TrackResponseDTO getTracks(int id, String token);

    void addPlaylist(PlaylistDTO playlist, String token);

    void updatePlaylistName(int id, PlaylistDTO playlistDTO, String token);

    void deletePlaylist(int id, String token);

    void addTrackToPlaylist(int playlistId, TrackDTO track, String token);

    void removeTrackFromPlaylist(int playlistId, int trackId, String token);
}
