package oose.dea.casvansummeren.business.services;

import oose.dea.casvansummeren.business.interfaces.IPlaylistOwnerDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistOwnerServiceTest {

    private static final int USER_ID_DUMMY = 1;
    private static final int PLAYLIST_ID_DUMMY = 1;

    PlaylistOwnerService sut;
    IPlaylistOwnerDAO mockPlaylistOwnerDAO;

    @BeforeEach
    void setup(){
        sut = new PlaylistOwnerService();
        mockPlaylistOwnerDAO = Mockito.mock(IPlaylistOwnerDAO.class);

        sut.setPlaylistOwnerDAO(mockPlaylistOwnerDAO);
    }

    @Test
    void IsPlaylistOwnerWithIdOfOwnerReturnsTrue(){
        //assign
        Mockito.when(mockPlaylistOwnerDAO.getPlaylistOwner(PLAYLIST_ID_DUMMY)).thenReturn(USER_ID_DUMMY);

        //act
        var actual = sut.isPlaylistOwner(USER_ID_DUMMY, PLAYLIST_ID_DUMMY);

        //assert
        assertTrue(actual);
    }

    @Test
    void IsPlaylistOwnerWithIdOfTheNonOwnerReturnsFalse(){
        //assign
        Mockito.when(mockPlaylistOwnerDAO.getPlaylistOwner(PLAYLIST_ID_DUMMY)).thenReturn(USER_ID_DUMMY + 1);

        //act
        var actual = sut.isPlaylistOwner(USER_ID_DUMMY, PLAYLIST_ID_DUMMY);

        //assert
        assertFalse(actual);
    }
}