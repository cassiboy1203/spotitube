package oose.dea.casvansummeren.business.services;

import oose.dea.casvansummeren.DTO.PlaylistDTO;
import oose.dea.casvansummeren.DTO.PlaylistsResponseDTO;
import oose.dea.casvansummeren.DTO.TrackDTO;
import oose.dea.casvansummeren.DTO.TrackResponseDTO;
import oose.dea.casvansummeren.api.interfaces.IPlaylistService;
import oose.dea.casvansummeren.business.interfaces.IPlaylistDAO;
import oose.dea.casvansummeren.business.interfaces.IPlaylistOwnerService;
import oose.dea.casvansummeren.exceptions.InvalidPermissionException;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class PlaylistService extends SpotitubeService implements IPlaylistService {

    private IPlaylistDAO playlistDAO;
    private IPlaylistOwnerService playlistOwnerService;

    @Inject
    public void setPlaylistDAO(IPlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    @Inject
    public void setPlaylistOwnerService(IPlaylistOwnerService playlistOwnerService) {
        this.playlistOwnerService = playlistOwnerService;
    }

    @Override
    public PlaylistsResponseDTO getPlaylists(String token) {
        var userid = authService.getUser(token);
        var playlistResponse = new PlaylistsResponseDTO();
        var playlists = playlistDAO.getPlaylists(userid);

        var playlistsLength = playlists.stream().mapToInt(playlistDTO -> playlistDTO.getLength()).sum();

        playlistResponse.setPlaylists(playlists);
        playlistResponse.setLength(playlistsLength);

        return playlistResponse;
    }

    @Override
    public TrackResponseDTO getTracks(int id, String token) {
        var userid = authService.getUser(token);
        var tracks = new TrackResponseDTO();
        tracks.setTracks(playlistDAO.getTracks(id));
        return tracks;
    }

    @Override
    public void addPlaylist(PlaylistDTO playlist, String token) {
        var userid = authService.getUser(token);
        playlistDAO.addPlaylist(playlist, userid);
    }

    @Override
    public void updatePlaylistName(int id, PlaylistDTO playlistDTO, String token) {
        var userId = authService.getUser(token);
        if (playlistOwnerService.isPlaylistOwner(userId, id)){
            playlistDAO.updatePlaylistName(id,playlistDTO);
        } else {
            throw new InvalidPermissionException();
        }
    }

    @Override
    public void deletePlaylist(int id, String token) {
        var userId = authService.getUser(token);
        if (playlistOwnerService.isPlaylistOwner(userId, id)){
            playlistDAO.deletePlaylist(id);
        } else {
            throw new InvalidPermissionException();
        }
    }

    @Override
    public void addTrackToPlaylist(int playlistId, TrackDTO track, String token) {
        var userId = authService.getUser(token);
        if (!playlistOwnerService.isPlaylistOwner(userId, playlistId))
            throw new InvalidPermissionException();

        playlistDAO.addTrackToPlaylist(playlistId,track);
    }

    @Override
    public void removeTrackFromPlaylist(int playlistId, int trackId, String token) {
        var userId = authService.getUser(token);
        if (!playlistOwnerService.isPlaylistOwner(userId, playlistId))
            throw new InvalidPermissionException();

        playlistDAO.removeTrackFromPlaylist(playlistId, trackId);
    }
}
