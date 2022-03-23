package oose.dea.casvansummeren.api.interfaces;

import oose.dea.casvansummeren.DTO.TrackResponseDTO;

public interface ITrackService {
    TrackResponseDTO getAvailableTracks(int playlistId, String token);
}
