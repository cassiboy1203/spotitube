package oose.dea.casvansummeren.DTO;

import java.util.ArrayList;
import java.util.List;

public class PlaylistsResponseDTO {
    private List<PlaylistDTO> playlists = new ArrayList<>();
    private int length = 100;

    public PlaylistsResponseDTO(){
        var playlist = new PlaylistDTO();
        playlist.setId(1);
        playlist.setName("Heavy Metal");
        playlist.setOwner(true);
        playlist.setTracks(new ArrayList<>());
        playlists.add(playlist);

        playlist = new PlaylistDTO();
        playlist.setId(2);
        playlist.setName("Pop");
        playlist.setOwner(false);
        playlist.setTracks(new ArrayList<>());
        playlists.add(playlist);
    }

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
