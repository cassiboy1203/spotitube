package oose.dea.casvansummeren.business.interfaces;

import oose.dea.casvansummeren.DTO.TrackDTO;

import java.util.List;

public interface ITrackDAO {
    List<TrackDTO> getAvailableTracks(int playlistId);
}
