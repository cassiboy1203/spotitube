package oose.dea.casvansummeren.business.services;

import oose.dea.casvansummeren.api.DTO.PlaylistDTO;
import oose.dea.casvansummeren.api.DTO.TrackDTO;
import oose.dea.casvansummeren.api.interfaces.IAuthService;
import oose.dea.casvansummeren.business.interfaces.IPlaylistDAO;
import oose.dea.casvansummeren.business.services.PlaylistService;
import oose.dea.casvansummeren.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistServiceTest {

    private PlaylistService sut;
    private IAuthService mockAuthService;
    private IPlaylistDAO mockPlaylistDAO;

    @BeforeEach
    void setup(){
        sut = new PlaylistService();

        mockAuthService = Mockito.mock(IAuthService.class);
        mockPlaylistDAO = Mockito.mock(IPlaylistDAO.class);

        sut.setAuthService(mockAuthService);
        sut.setPlaylistDAO(mockPlaylistDAO);
    }

    @Test
    void getPlaylistsReturnsListOfPlaylists(){
        //assign
        var expectedPlaylists = new ArrayList<PlaylistDTO>();
        var expectedLength = 100;

        expectedPlaylists.add(new PlaylistDTO(1,"test1",true, new ArrayList<>(),50));
        expectedPlaylists.add(new PlaylistDTO(2,"test2",false, new ArrayList<>(),50));

        Mockito.when(mockAuthService.getUser("")).thenReturn(1);
        Mockito.when(mockPlaylistDAO.getPlaylists()).thenReturn(expectedPlaylists);

        //act
        var actual = sut.getPlaylists("");

        //assert
        assertEquals(expectedPlaylists, actual.getPlaylists());
        assertEquals(expectedLength, actual.getLength());
    }

    @Test
    void getPlaylistsWrongTokenThrowsUserNotFoundException(){
        //assign
        Mockito.when(mockAuthService.getUser("")).thenThrow(UserNotFoundException.class);

        //assert
        assertThrows(UserNotFoundException.class, () -> {
            //act
            sut.getPlaylists("");
        });
    }

    @Test
    void getPlaylistReturnsOnePlaylist(){
        //assign
        var expected = new ArrayList<TrackDTO>();
        Mockito.when(mockPlaylistDAO.getTracks(0)).thenReturn(expected);

        //act
        var actual = sut.getTracks(0,"");

        //assert
        assertEquals(expected,actual);
    }
}