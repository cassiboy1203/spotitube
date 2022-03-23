package oose.dea.casvansummeren.data.mappers;

import oose.dea.casvansummeren.DTO.TrackDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrackDataMapper implements ITrackDataMapper{
    @Override
    public List<TrackDTO> getTracks(ResultSet result) throws SQLException {
        var tracks = new ArrayList<TrackDTO>();

        while (result.next()){
            var track = new TrackDTO();
            track.setId(result.getInt("id"));
            track.setTitle(result.getString("title"));
            track.setPerformer(result.getString("performer"));
            track.setDuration(result.getInt("duration"));
            track.setAlbum(result.getString("album"));
            track.setPlaycount(result.getInt("playcount"));
            var publicationDate = new Date();
            publicationDate.setTime(result.getInt("publicationDate"));
            var dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            track.setPublicationDate(dateFormat.format(publicationDate));
            track.setDescription(result.getString("description"));
            track.setOfflineAvailable(result.getBoolean("offlineAvailable"));

            tracks.add(track);
        }

        return tracks;
    }
}
