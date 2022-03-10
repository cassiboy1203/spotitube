package oose.dea.casvansummeren.api.interfaces;

import oose.dea.casvansummeren.api.DTO.PlaylistDTO;
import oose.dea.casvansummeren.api.DTO.PlaylistsResponseDTO;
import oose.dea.casvansummeren.api.DTO.TrackDTO;

import java.util.List;

public interface IPlaylistService {
    PlaylistsResponseDTO getPlaylists(String token);

    List<TrackDTO> getTracks(int id, String token);
}
