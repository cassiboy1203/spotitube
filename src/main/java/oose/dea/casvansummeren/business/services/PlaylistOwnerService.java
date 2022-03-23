package oose.dea.casvansummeren.business.services;

import oose.dea.casvansummeren.business.interfaces.IPlaylistOwnerDAO;
import oose.dea.casvansummeren.business.interfaces.IPlaylistOwnerService;

import javax.inject.Inject;

public class PlaylistOwnerService implements IPlaylistOwnerService {
    IPlaylistOwnerDAO playlistOwnerDAO;

    @Inject
    public void setPlaylistOwnerDAO(IPlaylistOwnerDAO playlistOwnerDAO) {
        this.playlistOwnerDAO = playlistOwnerDAO;
    }

    @Override
    public boolean isPlaylistOwner(int userId, int playlistId) {
        var owner = playlistOwnerDAO.getPlaylistOwner(playlistId);
        return owner == userId;
    }
}
