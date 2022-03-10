package oose.dea.casvansummeren.data;

import oose.dea.casvansummeren.api.DTO.PlaylistDTO;
import oose.dea.casvansummeren.api.DTO.TrackDTO;
import oose.dea.casvansummeren.business.interfaces.IPlaylistDAO;

import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistDAO implements IPlaylistDAO {
    @Override
    public List<PlaylistDTO> getPlaylists() {
        var playlists = new ArrayList<PlaylistDTO>();
        playlists.add(new PlaylistDTO());
        playlists.add(new PlaylistDTO());
        return playlists;
    }

    @Override
    public List<TrackDTO> getTracks(int id) {
        var tracks = new ArrayList<TrackDTO>();
        tracks.add(new TrackDTO());
        tracks.add(new TrackDTO());
        return tracks;
    }
}
