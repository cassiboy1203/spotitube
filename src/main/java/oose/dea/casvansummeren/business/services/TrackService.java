package oose.dea.casvansummeren.business.services;

import oose.dea.casvansummeren.DTO.TrackResponseDTO;
import oose.dea.casvansummeren.api.interfaces.ITrackService;
import oose.dea.casvansummeren.business.interfaces.IPlaylistOwnerService;
import oose.dea.casvansummeren.business.interfaces.ITrackDAO;
import oose.dea.casvansummeren.exceptions.InvalidPermissionException;

import javax.inject.Inject;

public class TrackService extends SpotitubeService implements ITrackService {

    ITrackDAO trackDAO;
    IPlaylistOwnerService playlistOwnerService;

    @Inject
    public void setTrackDAO(ITrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @Inject
    public void setPlaylistOwnerService(IPlaylistOwnerService playlistOwnerService) {
        this.playlistOwnerService = playlistOwnerService;
    }

    @Override
    public TrackResponseDTO getAvailableTracks(int playlistId, String token) {
        var userId = authService.getUser(token);
        if (!playlistOwnerService.isPlaylistOwner(userId, playlistId))
            throw new InvalidPermissionException();

        var tracks = trackDAO.getAvailableTracks(playlistId);
        var trackResponse = new TrackResponseDTO();
        trackResponse.setTracks(tracks);
        return trackResponse;
    }
}
