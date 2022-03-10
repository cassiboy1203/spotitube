package oose.dea.casvansummeren.business.services;

import oose.dea.casvansummeren.api.DTO.PlaylistsResponseDTO;
import oose.dea.casvansummeren.api.DTO.TrackDTO;
import oose.dea.casvansummeren.api.interfaces.IPlaylistService;
import oose.dea.casvansummeren.business.interfaces.IPlaylistDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;

@Default
public class PlaylistService extends SpotitubeService implements IPlaylistService {

    private IPlaylistDAO playlistDAO;

    @Inject
    public void setPlaylistDAO(IPlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    @Override
    public PlaylistsResponseDTO getPlaylists(String token) {
        var userid = authService.getUser(token);
        var playlistResponse = new PlaylistsResponseDTO();
        var playlists = playlistDAO.getPlaylists();

        //TODO: check if user is owner

        var playlistsLength = playlists.stream().mapToInt(playlistDTO -> playlistDTO.getLength()).sum();

        playlistResponse.setPlaylists(playlists);
        playlistResponse.setLength(playlistsLength);

        return playlistResponse;
    }

    @Override
    public List<TrackDTO> getTracks(int id, String token) {
        var userid = authService.getUser(token);
        return playlistDAO.getTracks(id);
    }
}
