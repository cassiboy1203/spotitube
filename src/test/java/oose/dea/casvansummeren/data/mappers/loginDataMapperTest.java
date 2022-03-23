package oose.dea.casvansummeren.data.mappers;

import oose.dea.casvansummeren.DTO.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class loginDataMapperTest {

    private static final int USER_ID_DUMMY = 1;
    private static final String USER_NAME_DUMMY = "dummy";
    private static final String USER_PASSWORD_DUMMY = "dummy";

    LoginDataMapper sut;
    ResultSet mockResult;

    @BeforeEach
    void setup(){
        sut = new LoginDataMapper();
        mockResult = Mockito.mock(ResultSet.class);
    }

    @Test
    void getUserInfoTest() {
        //assign
        var expected = new UserDTO();
        expected.setId(USER_ID_DUMMY);
        expected.setName(USER_NAME_DUMMY);
        expected.setPassword(USER_PASSWORD_DUMMY);

        try {
            Mockito.when(mockResult.next()).thenReturn(true).thenReturn(false);
            Mockito.when(mockResult.getInt("userid")).thenReturn(USER_ID_DUMMY);
            Mockito.when(mockResult.getString("username")).thenReturn(USER_NAME_DUMMY);
            Mockito.when(mockResult.getString("password")).thenReturn(USER_PASSWORD_DUMMY);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //act
        UserDTO actual = null;
        try {
            actual = sut.getUserInfo(mockResult);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //assert
        assertEquals(expected,actual);
    }

    @Test
    void getUserInfoTestWithNoUserReturnsNull(){
        //assign
        try {
            Mockito.when(mockResult.next()).thenReturn(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //act
        UserDTO actual = null;
        try {
            actual = sut.getUserInfo(mockResult);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //assert
        assertNull(actual);
    }
}