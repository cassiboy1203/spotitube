package oose.dea.casvansummeren.api.resourses;

import oose.dea.casvansummeren.api.DTO.PlaylistsResponseDTO;
import oose.dea.casvansummeren.api.DTO.TrackDTO;
import oose.dea.casvansummeren.api.interfaces.IPlaylistService;
import oose.dea.casvansummeren.exceptions.PlaylistNotFoundException;
import oose.dea.casvansummeren.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlaylistResourceTest {

    private PlaylistResource sut;
    private IPlaylistService mockPlaylistService;

    @BeforeEach
    void setup(){
        sut = new PlaylistResource();
        mockPlaylistService = Mockito.mock(IPlaylistService.class);

        sut.setPlaylistService(mockPlaylistService);
    }

    @Test
    void getPlaylistsReturnsPlaylist(){
        //assert
        var expected = new PlaylistsResponseDTO();
        Mockito.when(mockPlaylistService.getPlaylists("")).thenReturn(expected);

        //act
        var actual = sut.getPlaylists("");

        //assert
        assertEquals(expected, actual.getEntity());
    }

    @Test
    void getPlaylistsNotExistingTokenThrowsUserNotFoundException(){
        //assign
        Mockito.when(mockPlaylistService.getPlaylists("")).thenThrow(UserNotFoundException.class);

        //assert
        assertThrows(UserNotFoundException.class, () -> {
            //act
            sut.getPlaylists("");
        });
    }

    @Test
    void getTracksReturnsListOfTracks(){
        //assign
        var expected = new ArrayList<TrackDTO>();
        Mockito.when(mockPlaylistService.getTracks(0,"")).thenReturn(expected);

        //act
        var actual = sut.getTracks(1, "");

        //assert
        assertEquals(expected, actual.getEntity());
    }

    @Test
    void getTracksNotExistingTokenThrowsUserNotFoundException(){
        //assign
        Mockito.when(mockPlaylistService.getTracks(0,"")).thenThrow(UserNotFoundException.class);

        //assert
        assertThrows(UserNotFoundException.class, () -> {
            //act
            sut.getTracks(0,"");
        });
    }

    @Test
    void getTracksNotExistingPlaylistThrowsPlaylistNotFoundException(){
        //assign
        Mockito.when(mockPlaylistService.getTracks(0,"")).thenThrow(PlaylistNotFoundException.class);

        //assert
        assertThrows(PlaylistNotFoundException.class, () -> {
            //act
            sut.getTracks(0,"");
        });
    }
}