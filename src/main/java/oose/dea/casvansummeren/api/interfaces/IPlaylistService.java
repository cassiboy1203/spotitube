package oose.dea.casvansummeren.api.interfaces;

import oose.dea.casvansummeren.api.DTO.PlaylistDTO;
import oose.dea.casvansummeren.api.DTO.PlaylistsResponseDTO;
import oose.dea.casvansummeren.api.DTO.TrackDTO;
import oose.dea.casvansummeren.api.DTO.TrackResponseDTO;

import java.util.List;

public interface IPlaylistService {
    PlaylistsResponseDTO getPlaylists(String token);

    TrackResponseDTO getTracks(int id, String token);

    void addPlaylist(PlaylistDTO playlist, String token);

    void updatePlaylistName(int id, PlaylistDTO playlistDTO, String token);

    void deletePlaylist(int id, String token);
}
