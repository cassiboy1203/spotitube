package oose.dea.casvansummeren.api.resourses;

import oose.dea.casvansummeren.DTO.PlaylistDTO;
import oose.dea.casvansummeren.DTO.PlaylistsResponseDTO;
import oose.dea.casvansummeren.DTO.TrackDTO;
import oose.dea.casvansummeren.DTO.TrackResponseDTO;
import oose.dea.casvansummeren.api.interfaces.IPlaylistService;
import oose.dea.casvansummeren.exceptions.PlaylistNotFoundException;
import oose.dea.casvansummeren.exceptions.InvalidPermissionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlaylistResourceTest {

    private static final String TOKEN_DUMMY = "";
    private static final int PLAYLIST_ID_DUMMY = 1;
    private static final int TRACK_ID_DUMMY = 1;

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
        Mockito.when(mockPlaylistService.getPlaylists(TOKEN_DUMMY)).thenReturn(expected);

        //act
        var actual = sut.getPlaylists(TOKEN_DUMMY);

        //assert
        assertEquals(expected, actual.getEntity());
    }

    @Test
    void getPlaylistsNotExistingTokenThrowsUserNotFoundException(){
        //assign
        Mockito.when(mockPlaylistService.getPlaylists(TOKEN_DUMMY)).thenThrow(InvalidPermissionException.class);

        //assert
        assertThrows(InvalidPermissionException.class, () -> {
            //act
            sut.getPlaylists(TOKEN_DUMMY);
        });
    }

    @Test
    void getTracksReturnsListOfTracks(){
        //assign
        var expected = new TrackResponseDTO();
        expected.setTracks(new ArrayList<>());

        Mockito.when(mockPlaylistService.getTracks(PLAYLIST_ID_DUMMY,TOKEN_DUMMY)).thenReturn(expected);

        //act
        var actual = sut.getTracks(PLAYLIST_ID_DUMMY, TOKEN_DUMMY);

        //assert
        assertEquals(expected, actual.getEntity());
    }

    @Test
    void getTracksNotExistingTokenThrowsUserNotFoundException(){
        //assign
        Mockito.when(mockPlaylistService.getTracks(PLAYLIST_ID_DUMMY,TOKEN_DUMMY)).thenThrow(InvalidPermissionException.class);

        //assert
        assertThrows(InvalidPermissionException.class, () -> {
            //act
            sut.getTracks(PLAYLIST_ID_DUMMY,TOKEN_DUMMY);
        });
    }

    @Test
    void getTracksNotExistingPlaylistThrowsPlaylistNotFoundException(){
        //assign
        Mockito.when(mockPlaylistService.getTracks(PLAYLIST_ID_DUMMY,TOKEN_DUMMY)).thenThrow(PlaylistNotFoundException.class);

        //assert
        assertThrows(PlaylistNotFoundException.class, () -> {
            //act
            sut.getTracks(PLAYLIST_ID_DUMMY,TOKEN_DUMMY);
        });
    }

    @Test
    void addPlaylistReturnsAllPlaylists(){
        //assign
        var expected = new PlaylistsResponseDTO();
        var playlist = new PlaylistDTO();
        Mockito.when(mockPlaylistService.getPlaylists(TOKEN_DUMMY)).thenReturn(expected);

        //act
        var actual = sut.addPlaylist(playlist, TOKEN_DUMMY);

        //assert
        Mockito.verify(mockPlaylistService).addPlaylist(playlist, TOKEN_DUMMY);
        assertEquals(expected, actual.getEntity());
    }

    @Test
    void updatePlaylistNameReturnsAllPlaylists(){
        //assign
        var expected = new PlaylistsResponseDTO();
        var playlist = new PlaylistDTO();
        Mockito.when(mockPlaylistService.getPlaylists(TOKEN_DUMMY)).thenReturn(expected);

        //act
        var actual = sut.updatePlaylistName(PLAYLIST_ID_DUMMY, playlist, TOKEN_DUMMY);

        //assert
        Mockito.verify(mockPlaylistService).updatePlaylistName(PLAYLIST_ID_DUMMY ,playlist, TOKEN_DUMMY);
        assertEquals(expected, actual.getEntity());
    }

    @Test
    void deletePlaylistReturnsAllPlaylists(){
        //assign
        var expected = new PlaylistsResponseDTO();
        Mockito.when(mockPlaylistService.getPlaylists(TOKEN_DUMMY)).thenReturn(expected);

        //act
        var actual = sut.deletePlaylist(PLAYLIST_ID_DUMMY, TOKEN_DUMMY);

        //assert
        Mockito.verify(mockPlaylistService).deletePlaylist(PLAYLIST_ID_DUMMY, TOKEN_DUMMY);
        assertEquals(expected, actual.getEntity());
    }

    @Test
    void addTrackToPlaylistsReturnsUpdatedListOfTracks(){
        //assign
        var expected = new TrackResponseDTO();
        var track = new TrackDTO();
        Mockito.when(mockPlaylistService.getTracks(PLAYLIST_ID_DUMMY, TOKEN_DUMMY)).thenReturn(expected);

        //act
        var actual = sut.addTrackToPlaylist(PLAYLIST_ID_DUMMY, track, TOKEN_DUMMY);

        //assert
        Mockito.verify(mockPlaylistService).addTrackToPlaylist(PLAYLIST_ID_DUMMY, track, TOKEN_DUMMY);
        assertEquals(expected, actual.getEntity());
    }

    @Test
    void removeTrackFromPlaylistReturnsUpdatedListOfTracks(){
        //assign
        var expected = new TrackResponseDTO();
        Mockito.when(mockPlaylistService.getTracks(PLAYLIST_ID_DUMMY, TOKEN_DUMMY)).thenReturn(expected);

        //act
        var actual = sut.removeTrackFromPlaylist(PLAYLIST_ID_DUMMY, TRACK_ID_DUMMY, TOKEN_DUMMY);

        //assert
        Mockito.verify(mockPlaylistService).removeTrackFromPlaylist(PLAYLIST_ID_DUMMY, TRACK_ID_DUMMY, TOKEN_DUMMY);
        assertEquals(expected, actual.getEntity());
    }
}