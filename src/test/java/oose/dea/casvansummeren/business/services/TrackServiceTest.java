package oose.dea.casvansummeren.business.services;

import oose.dea.casvansummeren.DTO.TrackDTO;
import oose.dea.casvansummeren.api.interfaces.IAuthService;
import oose.dea.casvansummeren.business.interfaces.IPlaylistOwnerService;
import oose.dea.casvansummeren.business.interfaces.ITrackDAO;
import oose.dea.casvansummeren.exceptions.InvalidPermissionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TrackServiceTest {

    private static final String TOKEN_DUMMY = "";
    private static final int PLAYLIST_ID_DUMMY = 1;
    private static final int USER_ID_DUMMY = 1;

    TrackService sut;
    ITrackDAO mockTrackDAO;
    IAuthService mockAuthService;
    IPlaylistOwnerService mockPlaylistOwnerService;

    @BeforeEach
    void setup(){
        sut = new TrackService();
        mockTrackDAO = Mockito.mock(ITrackDAO.class);
        mockAuthService = Mockito.mock(IAuthService.class);
        mockPlaylistOwnerService = Mockito.mock(IPlaylistOwnerService.class);

        sut.setTrackDAO(mockTrackDAO);
        sut.setAuthService(mockAuthService);
        sut.setPlaylistOwnerService(mockPlaylistOwnerService);
    }

    @Test
    void getAvailableTracksReturnsATrackResponseOfTracksNotInPlaylist(){
        //assign
        var expected = new ArrayList<TrackDTO>();
        Mockito.when(mockAuthService.getUser(TOKEN_DUMMY)).thenReturn(USER_ID_DUMMY);
        Mockito.when(mockPlaylistOwnerService.isPlaylistOwner(USER_ID_DUMMY, PLAYLIST_ID_DUMMY)).thenReturn(true);
        Mockito.when(mockTrackDAO.getAvailableTracks(PLAYLIST_ID_DUMMY)).thenReturn(expected);

        //act
        var actual = sut.getAvailableTracks(PLAYLIST_ID_DUMMY, TOKEN_DUMMY);

        //assert
        Mockito.verify(mockAuthService).getUser(TOKEN_DUMMY);
        assertEquals(expected, actual.getTracks());
    }

    @Test
    void getAvailableTracksWithInvalidTokenThrowsInvalidPermissionException(){
        //assign
        Mockito.when(mockAuthService.getUser(TOKEN_DUMMY)).thenThrow(InvalidPermissionException.class);

        //assert
        assertThrows(InvalidPermissionException.class, () -> {
            //act
            sut.getAvailableTracks(PLAYLIST_ID_DUMMY, TOKEN_DUMMY);
        });
    }

    @Test
    void getAvailableTracksWithUserWhoIsNotTheOwnerThrowsInvalidPermissionException(){
        //assign
        Mockito.when(mockAuthService.getUser(TOKEN_DUMMY)).thenReturn(USER_ID_DUMMY);
        Mockito.when(mockPlaylistOwnerService.isPlaylistOwner(USER_ID_DUMMY, PLAYLIST_ID_DUMMY)).thenReturn(false);

        assertThrows(InvalidPermissionException.class, () -> {
            //act
            sut.getAvailableTracks(PLAYLIST_ID_DUMMY, TOKEN_DUMMY);
        });
    }
}