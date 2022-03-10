package oose.dea.casvansummeren.business.services;

import oose.dea.casvansummeren.api.interfaces.IAuthService;
import oose.dea.casvansummeren.business.services.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    LoginService sut;
    IAuthService mockAuthService;

    @BeforeEach
    void setup(){
        sut = new LoginService();

        mockAuthService = Mockito.mock(IAuthService.class);

        sut.setAuthService(mockAuthService);
    }

    @Test
    void generateResponseReturnsUsernameAndARandomUUID(){
        //assign
        var expectedUserName = "admin";
        Mockito.when(mockAuthService.generateToken()).thenReturn("1234-1234-1234");

        //act
        var actual = sut.generateResponse("admin");

        //assert
        assertEquals(expectedUserName, actual.getUser());
        assertEquals("1234-1234-1234", actual.getToken());
    }

    @Test
    void checkUserCorrectInfoReturnsTrue(){
        //assign

        //act
        var actual = sut.checkLogin("admin", "admin");

        //assert
        assertTrue(actual);
    }

    @Test
    void checkUserWrongInfoReturnsFalse(){
        //assign

        //act
        var actual = sut.checkLogin("test", "test");

        //assert
        assertFalse(actual);
    }
}