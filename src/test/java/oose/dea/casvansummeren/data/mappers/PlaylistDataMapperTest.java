package oose.dea.casvansummeren.data.mappers;

import oose.dea.casvansummeren.DTO.PlaylistDTO;
import oose.dea.casvansummeren.DTO.TrackDTO;
import oose.dea.casvansummeren.exceptions.DatabaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistDataMapperTest {

    private static final int USER_ID_DUMMY = 1;
    private static final int OWNER_ID_DUMMY = 1;

    PlaylistDataMapper sut;
    ResultSet mockResult;

    @BeforeEach
    void setup(){
        sut = new PlaylistDataMapper();
        mockResult = Mockito.mock(ResultSet.class);
    }

    @Test
    void getPlaylistsReturnsAListOfPlaylists(){
        //assign
        var expected = new ArrayList<PlaylistDTO>();

        var playlist1_dummy = new PlaylistDTO();
        var playlist2_dummy = new PlaylistDTO();

        playlist1_dummy.setId(1);
        playlist1_dummy.setName("playlist1");
        playlist1_dummy.setOwner(true);
        playlist1_dummy.setLength(1);
        playlist2_dummy.setId(2);
        playlist2_dummy.setName("playlist2");
        playlist2_dummy.setOwner(false);
        playlist2_dummy.setLength(1);
        expected.add(playlist1_dummy);
        expected.add(playlist2_dummy);

        try {
            Mockito.when(mockResult.next()).thenReturn(true).thenReturn(true).thenReturn(false);
            Mockito.when(mockResult.getInt("id")).thenReturn(playlist1_dummy.getId()).thenReturn(playlist2_dummy.getId());
            Mockito.when(mockResult.getString("name")).thenReturn(playlist1_dummy.getName()).thenReturn(playlist2_dummy.getName());
            Mockito.when(mockResult.getInt("owner")).thenReturn(USER_ID_DUMMY).thenReturn(USER_ID_DUMMY + 1);
            Mockito.when(mockResult.getInt("duration")).thenReturn(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<PlaylistDTO> actual = null;
        //act
        try {
            actual = sut.getPlaylists(mockResult, USER_ID_DUMMY);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //assert
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void getPlaylistOwnerReturnsIdOfOwner(){
        //assign
        var expected = OWNER_ID_DUMMY;

        try {
            Mockito.when(mockResult.next()).thenReturn(true);
            Mockito.when(mockResult.getInt("owner")).thenReturn(OWNER_ID_DUMMY);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //act
        int actual = 0;
        try {
            actual = sut.getPlaylistOwner(mockResult);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(expected, actual);
    }

    @Test
    void getPlaylistOwnerWithNoResultThrowsDatabaseException(){
        //assign
        try {
            Mockito.when(mockResult.next()).thenReturn(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //assert
        assertThrows(DatabaseException.class, () -> {
            //act
            sut.getPlaylistOwner(mockResult);
        });
    }
}