package oose.dea.casvansummeren.business.services;

import oose.dea.casvansummeren.api.DTO.PlaylistDTO;
import oose.dea.casvansummeren.api.DTO.TrackResponseDTO;
import oose.dea.casvansummeren.api.interfaces.IAuthService;
import oose.dea.casvansummeren.business.interfaces.IPlaylistDAO;
import oose.dea.casvansummeren.exceptions.InvalidPermissionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistServiceTest {

    private static final String TOKEN_DUMMY = "";

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

        Mockito.when(mockAuthService.getUser(TOKEN_DUMMY)).thenReturn(1);
        Mockito.when(mockPlaylistDAO.getPlaylists(1)).thenReturn(expectedPlaylists);

        //act
        var actual = sut.getPlaylists(TOKEN_DUMMY);

        //assert
        assertEquals(expectedPlaylists, actual.getPlaylists());
        assertEquals(expectedLength, actual.getLength());
    }

    @Test
    void getPlaylistsWrongTokenThrowsUserNotFoundException(){
        //assign
        Mockito.when(mockAuthService.getUser(TOKEN_DUMMY)).thenThrow(InvalidPermissionException.class);

        //assert
        assertThrows(InvalidPermissionException.class, () -> {
            //act
            sut.getPlaylists("");
        });
    }

    @Test
    void getPlaylistReturnsOnePlaylist(){
        //assign
        var expected = new TrackResponseDTO();
        expected.setTracks(new ArrayList<>());
        Mockito.when(mockPlaylistDAO.getTracks(0)).thenReturn(expected.getTracks());

        //act
        var actual = sut.getTracks(0,TOKEN_DUMMY);

        //assert
        assertEquals(expected.getTracks(),actual.getTracks());
    }

    @Test
    void addPlaylistWithValidTokenSavesPlaylist(){
        //assign
        var playlist = new PlaylistDTO();
        Mockito.when(mockAuthService.getUser(TOKEN_DUMMY)).thenReturn(1);

        //act
        sut.addPlaylist(playlist, TOKEN_DUMMY);

        //assert
        Mockito.verify(mockAuthService).getUser(TOKEN_DUMMY);
        Mockito.verify(mockPlaylistDAO).addPlaylist(playlist, 1);
    }

    @Test
    void addPlaylistWithInvalidTokenThrowsUserNotFoundException(){
        //TODO: ask if useful test
        Mockito.when(mockAuthService.getUser(TOKEN_DUMMY)).thenThrow(InvalidPermissionException.class);

        //assert
        assertThrows(InvalidPermissionException.class, () -> {
            //act
            sut.addPlaylist(new PlaylistDTO(), TOKEN_DUMMY);
        });
    }

    @Test
    void updatePlaylistNameWithIdOfOwnerAndValidTokenSavesPlaylistNewName(){
        //assign
        var playlist = new PlaylistDTO();
        Mockito.when(mockAuthService.getUser(TOKEN_DUMMY)).thenReturn(1);
        Mockito.when(mockPlaylistDAO.getPlaylistOwner(1)).thenReturn(1);

        //act
        sut.updatePlaylistName(1, playlist, TOKEN_DUMMY);

        //assert
        Mockito.verify(mockAuthService).getUser(TOKEN_DUMMY);
        Mockito.verify(mockPlaylistDAO).updatePlaylistName(1, playlist);
    }

    @Test
    void updatePlaylistNameWithIdThatIsNotOwnerThrowsInvalidPermissionException(){
        //assign
        var playlist = new PlaylistDTO();
        Mockito.when(mockAuthService.getUser(TOKEN_DUMMY)).thenReturn(1);
        Mockito.when(mockPlaylistDAO.getPlaylistOwner(1)).thenReturn(2);

        //assert
        assertThrows(InvalidPermissionException.class, () -> {
            //act
            sut.updatePlaylistName(1, playlist, TOKEN_DUMMY);
        });
    }

    @Test
    void deletePlaylistWithIdThatOfOwnerDeletesPlaylist(){
        //assign
        Mockito.when(mockAuthService.getUser(TOKEN_DUMMY)).thenReturn(1);
        Mockito.when(mockPlaylistDAO.getPlaylistOwner(1)).thenReturn(1);

        //act
        sut.deletePlaylist(1, TOKEN_DUMMY);

        //assert
        Mockito.verify(mockPlaylistDAO).deletePlaylist(1);
    }

    @Test
    void deletePlaylistWithIdThatIsNotOwnerThrowsInvalidPermissionException(){
        //assign
        Mockito.when(mockAuthService.getUser(TOKEN_DUMMY)).thenReturn(1);
        Mockito.when(mockPlaylistDAO.getPlaylistOwner(1)).thenReturn(2);

        //assert
        assertThrows(InvalidPermissionException.class, () -> {
            //act
            sut.deletePlaylist(1, TOKEN_DUMMY);
        });
    }
}