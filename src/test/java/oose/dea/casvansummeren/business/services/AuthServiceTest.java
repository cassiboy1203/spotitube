package oose.dea.casvansummeren.business.services;

import oose.dea.casvansummeren.business.interfaces.IAuthDAO;
import oose.dea.casvansummeren.exceptions.InvalidPermissionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    private static final String TOKEN_DUMMY = "";
    private static final int USER_ID_DUMMY = 1;

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
        Mockito.when(mockAuthDAO.getUserId(TOKEN_DUMMY)).thenReturn(expected);

        //act
        var actual = sut.getUser(TOKEN_DUMMY);

        //assert
        assertEquals(expected, actual);
    }

    @Test
    void getUserWithWrongTokenThrowsUserNotFoundException(){
        //assign
        Mockito.when(mockAuthDAO.getUserId(TOKEN_DUMMY)).thenThrow(InvalidPermissionException.class);

        //assert
        assertThrows(InvalidPermissionException.class, () ->{
            //act
            sut.getUser("");
        });
    }

    @Test
    void saveUserExecutesSaveUserInAuthDAO(){
        //assign
        var user = USER_ID_DUMMY;
        var token = TOKEN_DUMMY;

        //act
        sut.saveToken(token, user);

        //assert
        Mockito.verify(mockAuthDAO).saveAuth(token, user);
    }
}