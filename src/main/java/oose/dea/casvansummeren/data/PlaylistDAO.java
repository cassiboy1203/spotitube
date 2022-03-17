package oose.dea.casvansummeren.data;

import oose.dea.casvansummeren.api.DTO.PlaylistDTO;
import oose.dea.casvansummeren.api.DTO.TrackDTO;
import oose.dea.casvansummeren.business.interfaces.IPlaylistDAO;
import oose.dea.casvansummeren.exceptions.DatabaseException;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistDAO implements IPlaylistDAO {

    private static final String GET_PLAYLISTS_STATEMENT = "SELECT * FROM playlists";
    private static final String GET_PLAYLIST_LENGTH_STATEMENT = "SELECT SUM(duration) as totalDuration FROM tracks t, playlistTracks pt WHERE t.id = pt.track AND pt.playlist = ?";
    private static final String GET_TRACKS_STATEMENT = "SELECT t.* FROM tracks t, playlistTracks pt WHERE t.id = pt.track AND pt.playlist = ?";
    private static final String INSERT_PLAYLIST_STATEMENT = "INSERT INTO playlists(name, owner) VALUES(?, ?)";
    private static final String UPDATE_PLAYLIST_NAME_STATEMENT = "UPDATE playlists SET name = ? WHERE id = ?";
    private static final String GET_PLAYLIST_OWNER_STATEMENT = "SELECT owner FROM playlists WHERE id = ?";
    private static final String DELETE_PLAYLIST_STATEMENT_1 = "DELETE FROM playlistTracks WHERE playlist = ?";
    private static final String DELETE_PLAYLIST_STATEMENT_2 = "DELETE FROM playlists WHERE id = ?";

    private IDatabaseService databaseService;

    @Inject
    public void setDatabaseService(IDatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public List<PlaylistDTO> getPlaylists(int user) {
        try (var conn = databaseService.connect();
             var stmt = conn.prepareStatement(GET_PLAYLISTS_STATEMENT)
        ){
            var result = stmt.executeQuery();

            var playlists = new ArrayList<PlaylistDTO>();

            while (result.next()){
                var playlist = new PlaylistDTO();
                playlist.setId(result.getInt("id"));
                playlist.setName(result.getString("name"));
                if (result.getInt("owner") == user)
                    playlist.setOwner(true);
                playlist.setLength(getPlaylistLength(playlist.getId()));

                playlists.add(playlist);
            }

            return playlists;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    private int getPlaylistLength(int id){
        try (var conn = databaseService.connect();
             var stmt = conn.prepareStatement(GET_PLAYLIST_LENGTH_STATEMENT);
        ){
            stmt.setInt(1, id);

            var result = stmt.executeQuery();

            if (result.next()){
                return result.getInt("totalDuration");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
        return 0;
    }

    @Override
    public List<TrackDTO> getTracks(int id) {
        try (var conn  = databaseService.connect();
             var stmt = conn.prepareStatement(GET_TRACKS_STATEMENT)
        ){
            stmt.setInt(1, id);

            var result = stmt.executeQuery();

            var tracks = new ArrayList<TrackDTO>();

            while (result.next()){
                var track = new TrackDTO();
                track.setId(result.getInt("id"));
                track.setTitle(result.getString("title"));
                track.setPerformer(result.getString("performer"));
                track.setDuration(result.getInt("duration"));
                track.setAlbum(result.getString("album"));
                track.setPlaycount(result.getInt("playcount"));
                //TODO: convert epoch timestamp to human readable date
                var publicationDate = result.getInt("publicationDate");
                track.setDescription(result.getString("description"));
                track.setOfflineAvailable(result.getBoolean("offlineAvailable"));

                tracks.add(track);
            }

            return tracks;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    @Override
    public void addPlaylist(PlaylistDTO playlist, int ownerId) {
        try ( var conn = databaseService.connect();
              var stmt = conn.prepareStatement(INSERT_PLAYLIST_STATEMENT)
        ){
            stmt.setString(1, playlist.getName());
            stmt.setInt(2, ownerId);

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    @Override
    public void updatePlaylistName(int id, PlaylistDTO playlist) {
        try ( var conn = databaseService.connect();
              var stmt = conn.prepareStatement(UPDATE_PLAYLIST_NAME_STATEMENT)
        ){
            stmt.setString(1, playlist.getName());
            stmt.setInt(2, id);

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    @Override
    public int getPlaylistOwner(int id) {
        try ( var conn = databaseService.connect();
              var stmt = conn.prepareStatement(GET_PLAYLIST_OWNER_STATEMENT)
        ){
            stmt.setInt(1, id);

            var result = stmt.executeQuery();

            if (result.next()){
                return result.getInt("owner");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
        throw new DatabaseException();
    }

    @Override
    public void deletePlaylist(int id) {
        try (var conn = databaseService.connect();
             var stmt = conn.prepareStatement(DELETE_PLAYLIST_STATEMENT_1);
             var stmt2 = conn.prepareStatement(DELETE_PLAYLIST_STATEMENT_2)
        ){
            conn.setAutoCommit(false);

            stmt.setInt(1, id);
            stmt2.setInt(1, id);

            stmt.execute();
            stmt2.execute();

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }
}
