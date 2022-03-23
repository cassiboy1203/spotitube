package oose.dea.casvansummeren.DTO;

import java.util.List;

public class TrackResponseDTO {
    private List<TrackDTO> tracks;

    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }
}
