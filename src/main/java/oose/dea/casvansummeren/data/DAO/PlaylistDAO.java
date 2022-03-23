package oose.dea.casvansummeren.data.DAO;

import oose.dea.casvansummeren.DTO.PlaylistDTO;
import oose.dea.casvansummeren.DTO.TrackDTO;
import oose.dea.casvansummeren.business.interfaces.IPlaylistOwnerDAO;
import oose.dea.casvansummeren.business.interfaces.IPlaylistDAO;
import oose.dea.casvansummeren.data.IDatabaseService;
import oose.dea.casvansummeren.data.mappers.IPlaylistDataMapper;
import oose.dea.casvansummeren.data.mappers.ITrackDataMapper;
import oose.dea.casvansummeren.exceptions.DatabaseException;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistDAO implements IPlaylistDAO, IPlaylistOwnerDAO {

    private static final String GET_PLAYLISTS_STATEMENT = "select sum(t.duration) as totalDuration from playlists p join playlistTracks pT on p.id = pT.playlist join tracks t on t.id = pT.track";
    private static final String GET_TRACKS_STATEMENT = "SELECT t.* FROM tracks t, playlistTracks pt WHERE t.id = pt.track AND pt.playlist = ?";
    private static final String INSERT_PLAYLIST_STATEMENT = "INSERT INTO playlists(name, owner) VALUES(?, ?)";
    private static final String UPDATE_PLAYLIST_NAME_STATEMENT = "UPDATE playlists SET name = ? WHERE id = ?";
    private static final String GET_PLAYLIST_OWNER_STATEMENT = "SELECT owner FROM playlists WHERE id = ?";
    private static final String DELETE_ALL_TRACKS_FROM_PLAYLIST_STATEMENT = "DELETE FROM playlistTracks WHERE playlist = ?";
    private static final String DELETE_PLAYLIST_STATEMENT = "DELETE FROM playlists WHERE id = ?";
    private static final String ADD_TRACK_TO_PLAYLIST_STATEMENT = "INSERT INTO playlistTracks(playlist, track) VALUES (?,?)";
    private static final String UPDATE_OFFLINE_AVAILABLE_OF_TRACK_STATEMENT = "UPDATE tracks SET offlineAvailable = ? WHERE id = ?";
    private static final String DELETE_TRACK_FROM_PLAYLIST_STATEMENT = "DELETE FROM playlistTracks WHERE playlist = ? AND track = ?";

    private IDatabaseService databaseService;
    private IPlaylistDataMapper playlistDataMapper;
    private ITrackDataMapper trackDataMapper;

    @Inject
    public void setTrackDataMapper(ITrackDataMapper trackDataMapper) {
        this.trackDataMapper = trackDataMapper;
    }

    @Inject
    public void setDatabaseService(IDatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Inject
    public void setPlaylistDataMapper(IPlaylistDataMapper playlistDataMapper) {
        this.playlistDataMapper = playlistDataMapper;
    }

    @Override
    public List<PlaylistDTO> getPlaylists(int user) {
        try (var conn = databaseService.connect();
             var stmt = conn.prepareStatement(GET_PLAYLISTS_STATEMENT)
        ){
            var result = stmt.executeQuery();

            return playlistDataMapper.getPlaylists(result, user);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    @Override
    public List<TrackDTO> getTracks(int id) {
        try (var conn  = databaseService.connect();
             var stmt = conn.prepareStatement(GET_TRACKS_STATEMENT)
        ){
            stmt.setInt(1, id);

            var result = stmt.executeQuery();

            return trackDataMapper.getTracks(result);

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

            return playlistDataMapper.getPlaylistOwner(result);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    @Override
    public void deletePlaylist(int id) {
        try (var conn = databaseService.connect();
             var stmt = conn.prepareStatement(DELETE_ALL_TRACKS_FROM_PLAYLIST_STATEMENT);
             var stmt2 = conn.prepareStatement(DELETE_PLAYLIST_STATEMENT)
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

    @Override
    public void addTrackToPlaylist(int playlistId, TrackDTO track) {
        try (var conn = databaseService.connect();
             var stmt = conn.prepareStatement(ADD_TRACK_TO_PLAYLIST_STATEMENT);
             var stmt2 = conn.prepareStatement(UPDATE_OFFLINE_AVAILABLE_OF_TRACK_STATEMENT)
        ){
            stmt.setInt(1, playlistId);
            stmt.setInt(2, track.getId());

            stmt2.setBoolean(1, track.isOfflineAvailable());
            stmt2.setInt(2, track.getId());

            conn.setAutoCommit(false);

            stmt.execute();
            stmt2.execute();

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    @Override
    public void removeTrackFromPlaylist(int playlistId, int trackId) {
        try (var conn = databaseService.connect();
             var stmt = conn.prepareStatement(DELETE_TRACK_FROM_PLAYLIST_STATEMENT)
        ){
            stmt.setInt(1, playlistId);
            stmt.setInt(2, trackId);

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }
}
