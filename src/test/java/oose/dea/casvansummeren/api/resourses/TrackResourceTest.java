package oose.dea.casvansummeren.api.resourses;

import oose.dea.casvansummeren.DTO.TrackResponseDTO;
import oose.dea.casvansummeren.api.interfaces.ITrackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class TrackResourceTest {

    private static final String TOKEN_DUMMY = "";
    public static final int PLAYLIST_ID_DUMMY = 1;

    TrackResource sut;
    ITrackService mockTrackService;

    @BeforeEach
    void setup(){
        sut = new TrackResource();
        mockTrackService = Mockito.mock(ITrackService.class);

        sut.setTrackService(mockTrackService);
    }

    @Test
    void getAvailableTracksReturnAllTracksNotInPlaylist(){
        //assign
        var expected = new TrackResponseDTO();
        Mockito.when(mockTrackService.getAvailableTracks(PLAYLIST_ID_DUMMY, TOKEN_DUMMY)).thenReturn(expected);

        //act
        var actual = sut.GetAvailableTracks(PLAYLIST_ID_DUMMY, TOKEN_DUMMY);

        //assert
        assertEquals(expected, actual.getEntity());
    }
}