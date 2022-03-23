package oose.dea.casvansummeren.business.services;

import oose.dea.casvansummeren.DTO.PlaylistDTO;
import oose.dea.casvansummeren.DTO.TrackDTO;
import oose.dea.casvansummeren.DTO.TrackResponseDTO;
import oose.dea.casvansummeren.api.interfaces.IAuthService;
import oose.dea.casvansummeren.business.interfaces.IPlaylistDAO;
import oose.dea.casvansummeren.business.interfaces.IPlaylistOwnerService;
import oose.dea.casvansummeren.exceptions.InvalidPermissionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistServiceTest {

    private static final String TOKEN_DUMMY = "";
    private static final int PLAYLIST_ID_DUMMY = 1;
    private static final int USER_ID_DUMMY = 1;
    private static final int TRACK_ID_DUMMY = 1;
    private static final int OWNER_ID_DUMMY = 1;

    private PlaylistService sut;
    private IAuthService mockAuthService;
    private IPlaylistDAO mockPlaylistDAO;
    private IPlaylistOwnerService mockPlaylistOwnerService;

    @BeforeEach
    void setup(){
        sut = new PlaylistService();

        mockAuthService = Mockito.mock(IAuthService.class);
        mockPlaylistDAO = Mockito.mock(IPlaylistDAO.class);
        mockPlaylistOwnerService = Mockito.mock(IPlaylistOwnerService.class);

        sut.setAuthService(mockAuthService);
        sut.setPlaylistDAO(mockPlaylistDAO);
        sut.setPlaylistOwnerService(mockPlaylistOwnerService);
    }

    @Test
    void getPlaylistsReturnsListOfPlaylists(){
        //assign
        var expectedPlaylists = new ArrayList<PlaylistDTO>();
        var expectedLength = 100;

        expectedPlaylists.add(new PlaylistDTO(1,"test1",true, new ArrayList<>(),50));
        expectedPlaylists.add(new PlaylistDTO(2,"test2",false, new ArrayList<>(),50));

        Mockito.when(mockAuthService.getUser(TOKEN_DUMMY)).thenReturn(1);
        Mockito.when(mockPlaylistDAO.getPlaylists(USER_ID_DUMMY)).thenReturn(expectedPlaylists);

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
        Mockito.when(mockPlaylistDAO.getTracks(PLAYLIST_ID_DUMMY)).thenReturn(expected.getTracks());

        //act
        var actual = sut.getTracks(PLAYLIST_ID_DUMMY,TOKEN_DUMMY);

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
        Mockito.verify(mockPlaylistDAO).addPlaylist(playlist, OWNER_ID_DUMMY);
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
        Mockito.when(mockAuthService.getUser(TOKEN_DUMMY)).thenReturn(USER_ID_DUMMY);
        Mockito.when(mockPlaylistOwnerService.isPlaylistOwner(USER_ID_DUMMY, PLAYLIST_ID_DUMMY)).thenReturn(true);

        //act
        sut.updatePlaylistName(PLAYLIST_ID_DUMMY, playlist, TOKEN_DUMMY);

        //assert
        Mockito.verify(mockAuthService).getUser(TOKEN_DUMMY);
        Mockito.verify(mockPlaylistDAO).updatePlaylistName(PLAYLIST_ID_DUMMY, playlist);
    }

    @Test
    void updatePlaylistNameWithIdThatIsNotOwnerThrowsInvalidPermissionException(){
        //assign
        var playlist = new PlaylistDTO();
        Mockito.when(mockAuthService.getUser(TOKEN_DUMMY)).thenReturn(1);
        Mockito.when(mockPlaylistOwnerService.isPlaylistOwner(USER_ID_DUMMY, PLAYLIST_ID_DUMMY)).thenReturn(false);

        //assert
        assertThrows(InvalidPermissionException.class, () -> {
            //act
            sut.updatePlaylistName(PLAYLIST_ID_DUMMY, playlist, TOKEN_DUMMY);
        });
    }

    @Test
    void deletePlaylistWithIdThatOfOwnerDeletesPlaylist(){
        //assign
        Mockito.when(mockAuthService.getUser(TOKEN_DUMMY)).thenReturn(1);
        Mockito.when(mockPlaylistOwnerService.isPlaylistOwner(USER_ID_DUMMY, PLAYLIST_ID_DUMMY)).thenReturn(true);

        //act
        sut.deletePlaylist(PLAYLIST_ID_DUMMY, TOKEN_DUMMY);

        //assert
        Mockito.verify(mockPlaylistDAO).deletePlaylist(PLAYLIST_ID_DUMMY);
    }

    @Test
    void deletePlaylistWithIdThatIsNotOwnerThrowsInvalidPermissionException(){
        //assign
        Mockito.when(mockAuthService.getUser(TOKEN_DUMMY)).thenReturn(1);
        Mockito.when(mockPlaylistOwnerService.isPlaylistOwner(USER_ID_DUMMY, PLAYLIST_ID_DUMMY)).thenReturn(false);

        //assert
        assertThrows(InvalidPermissionException.class, () -> {
            //act
            sut.deletePlaylist(PLAYLIST_ID_DUMMY, TOKEN_DUMMY);
        });
    }

    @Test
    void addTrackToPlaylistSavesTrack(){
        //assign
        var track = new TrackDTO();
        track.setId(1);
        Mockito.when(mockAuthService.getUser(TOKEN_DUMMY)).thenReturn(USER_ID_DUMMY);
        Mockito.when(mockPlaylistOwnerService.isPlaylistOwner(USER_ID_DUMMY, PLAYLIST_ID_DUMMY)).thenReturn(true);

        //act
        sut.addTrackToPlaylist(PLAYLIST_ID_DUMMY, track, TOKEN_DUMMY);

        //assert
        Mockito.verify(mockPlaylistDAO).addTrackToPlaylist(PLAYLIST_ID_DUMMY, track);
    }

    @Test
    void AddTrackToPlaylistWithInvalidTokenThrowsInvalidPermissionException(){
        //assign
        var track = new TrackDTO();
        Mockito.when(mockAuthService.getUser(TOKEN_DUMMY)).thenThrow(InvalidPermissionException.class);

        //assert
        assertThrows(InvalidPermissionException.class, () ->{
            //act
            sut.addTrackToPlaylist(PLAYLIST_ID_DUMMY,track,TOKEN_DUMMY);
        });
    }

    @Test
    void AddTrackToPlaylistWithNonOwnerIdThrowsInvalidPermissionException(){
        //assign
        var track = new TrackDTO();
        Mockito.when(mockAuthService.getUser(TOKEN_DUMMY)).thenReturn(1);
        Mockito.when(mockPlaylistOwnerService.isPlaylistOwner(USER_ID_DUMMY, PLAYLIST_ID_DUMMY)).thenReturn(false);

        //assert
        assertThrows(InvalidPermissionException.class, () -> {
            //act
            sut.addTrackToPlaylist(PLAYLIST_ID_DUMMY, track, TOKEN_DUMMY);
        });
    }

    @Test
    void removeTrackFromPlaylistTest(){
        //assign
        Mockito.when(mockAuthService.getUser(TOKEN_DUMMY)).thenReturn(1);
        Mockito.when(mockPlaylistOwnerService.isPlaylistOwner(USER_ID_DUMMY, PLAYLIST_ID_DUMMY)).thenReturn(true);

        //act
        sut.removeTrackFromPlaylist(PLAYLIST_ID_DUMMY, TRACK_ID_DUMMY, TOKEN_DUMMY);

        //assert
        Mockito.verify(mockPlaylistDAO).removeTrackFromPlaylist(PLAYLIST_ID_DUMMY, TRACK_ID_DUMMY);
    }

    @Test
    void removeTrackFromPlaylistInvalidTokenThrowsInvalidPermissionException(){
        //assign
        Mockito.when(mockAuthService.getUser(TOKEN_DUMMY)).thenThrow(InvalidPermissionException.class);

        //assert
        assertThrows(InvalidPermissionException.class, () -> {
            //act
            sut.removeTrackFromPlaylist(PLAYLIST_ID_DUMMY, TRACK_ID_DUMMY, TOKEN_DUMMY);
        });
    }

    @Test
    void removeTrackFromPlaylistNonOwnerIdThrowsInvalidPermissionException(){
        //assign
        Mockito.when(mockAuthService.getUser(TOKEN_DUMMY)).thenReturn(1);
        Mockito.when(mockPlaylistOwnerService.isPlaylistOwner(USER_ID_DUMMY, PLAYLIST_ID_DUMMY)).thenReturn(false);

        //assert
        assertThrows(InvalidPermissionException.class, () -> {
            //act
            sut.removeTrackFromPlaylist(PLAYLIST_ID_DUMMY, TRACK_ID_DUMMY, TOKEN_DUMMY);
        });
    }
}