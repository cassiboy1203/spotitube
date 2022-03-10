package oose.dea.casvansummeren.business.services;

import oose.dea.casvansummeren.business.interfaces.IAuthDAO;
import oose.dea.casvansummeren.business.services.AuthService;
import oose.dea.casvansummeren.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    AuthService sut;
    IAuthDAO mockAuthDAO;

    @BeforeEach
    void setup(){
        sut = new AuthService();

        mockAuthDAO = Mockito.mock(IAuthDAO.class);

        sut.setAuthDAO(mockAuthDAO);
    }

    @Test
    void generateTokenGeneratesAUUID(){
        //assign

        //act
        var actual = sut.generateToken();

        //assert
        assertDoesNotThrow(() -> UUID.fromString(actual));
    }

    @Test
    void getUserWithCorrectTokenGivesBackUserID(){
        //assign
        var expected = 1;
        Mockito.when(mockAuthDAO.getUserId("")).thenReturn(expected);

        //act
        var actual = sut.getUser("");

        //assert
        assertEquals(expected, actual);
    }

    @Test
    void getUserWithWrongTokenThrowsUserNotFoundException(){
        //assign
        Mockito.when(mockAuthDAO.getUserId("")).thenThrow(UserNotFoundException.class);

        //assert
        assertThrows(UserNotFoundException.class, () ->{
            //act
            sut.getUser("");
        });
    }

    @Test
    void saveUserExecutesSaveUserInAuthDAO(){
        //assign
        var username = "test";
        var token = "1234-1234-1234";

        //act
        sut.saveToken(token, username);

        //assert
        Mockito.verify(mockAuthDAO).saveAuth(token, username);
    }
}