package oose.dea.casvansummeren.data.DAO;

import oose.dea.casvansummeren.DTO.TrackDTO;
import oose.dea.casvansummeren.business.interfaces.ITrackDAO;
import oose.dea.casvansummeren.data.IDatabaseService;
import oose.dea.casvansummeren.data.mappers.ITrackDataMapper;
import oose.dea.casvansummeren.exceptions.DatabaseException;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

public class TrackDAO implements ITrackDAO {

    private static final String GET_AVAILABLE_TRACKS_STATEMENT = "SELECT * FROM tracks WHERE id NOT IN (SELECT track FROM playlistTracks WHERE playlist = ?)";

    private IDatabaseService databaseService;
    private ITrackDataMapper trackDataMapper;

    @Inject
    public void setTrackDataMapper(ITrackDataMapper trackDataMapper) {
        this.trackDataMapper = trackDataMapper;
    }

    @Inject
    public void setDatabaseService(IDatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public List<TrackDTO> getAvailableTracks(int playlistId) {
        try (var conn = databaseService.connect();
             var stmt = conn.prepareStatement(GET_AVAILABLE_TRACKS_STATEMENT);
        ){
            stmt.setInt(1, playlistId);

            var result = stmt.executeQuery();

            return trackDataMapper.getTracks(result);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }
}
