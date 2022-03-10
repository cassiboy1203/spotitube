package oose.dea.casvansummeren.api.DTO;

import javax.sound.midi.Track;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDTO {
    private int id = 1;
    private String name = "a";
    private boolean owner;
    private List<TrackDTO> tracks = new ArrayList<>();
    private int length = 1;

    public PlaylistDTO() {
    }

    public PlaylistDTO(int id, String name, boolean owner, List<TrackDTO> tracks, int length) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
