package oose.dea.casvansummeren.data.mappers;

import oose.dea.casvansummeren.DTO.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrackDataMapperTest {

    TrackDataMapper sut;
    ResultSet mockResult;

    @BeforeEach
    void setup(){
        sut = new TrackDataMapper();
        mockResult = Mockito.mock(ResultSet.class);
    }

    @Test
    void getTracksReturnsListOfTracks(){
        //assign
        var expected = new ArrayList<TrackDTO>();
        var track1_dummy = new TrackDTO();
        track1_dummy.setId(1);
        track1_dummy.setTitle("track1");
        track1_dummy.setPerformer("performer1");
        track1_dummy.setDuration(1);
        track1_dummy.setAlbum("album1");
        track1_dummy.setPlaycount(1);
        track1_dummy.setPublicationDate("01-01-1970");
        track1_dummy.setDescription("description1");
        track1_dummy.setOfflineAvailable(false);

        var track2_dummy = new TrackDTO();
        track2_dummy.setId(1);
        track2_dummy.setTitle("track2");
        track2_dummy.setPerformer("performer2");
        track2_dummy.setDuration(1);
        track2_dummy.setAlbum("album2");
        track2_dummy.setPlaycount(1);
        track2_dummy.setPublicationDate("01-01-1970");
        track2_dummy.setDescription("description2");
        track2_dummy.setOfflineAvailable(false);

        expected.add(track1_dummy);
        expected.add(track2_dummy);

        try {
            Mockito.when(mockResult.next()).thenReturn(true).thenReturn(true).thenReturn(false);
            Mockito.when(mockResult.getInt("id")).thenReturn(track1_dummy.getId()).thenReturn(track2_dummy.getId());
            Mockito.when(mockResult.getString("title")).thenReturn(track1_dummy.getTitle()).thenReturn(track2_dummy.getTitle());
            Mockito.when(mockResult.getString("performer")).thenReturn(track1_dummy.getPerformer()).thenReturn(track2_dummy.getPerformer());
            Mockito.when(mockResult.getInt("duration")).thenReturn(1);
            Mockito.when(mockResult.getString("album")).thenReturn(track1_dummy.getAlbum()).thenReturn(track2_dummy.getAlbum());
            Mockito.when(mockResult.getInt("playcount")).thenReturn(1);
            Mockito.when(mockResult.getInt("publicationDate")).thenReturn(0);
            Mockito.when(mockResult.getString("description")).thenReturn(track1_dummy.getDescription()).thenReturn(track2_dummy.getDescription());
            Mockito.when(mockResult.getBoolean("offlineAvailable")).thenReturn(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //act
        List<TrackDTO> actual = null;
        try {
            actual = sut.getTracks(mockResult);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //assert
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

}