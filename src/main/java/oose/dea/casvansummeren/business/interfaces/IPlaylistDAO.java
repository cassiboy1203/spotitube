package oose.dea.casvansummeren.business.interfaces;

import oose.dea.casvansummeren.api.DTO.PlaylistDTO;
import oose.dea.casvansummeren.api.DTO.TrackDTO;

import java.util.List;

public interface IPlaylistDAO {
    List<PlaylistDTO> getPlaylists();

    List<TrackDTO> getTracks(int id);
}
