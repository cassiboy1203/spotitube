package oose.dea.casvansummeren.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    LoginService sut;

    @BeforeEach
    void setup(){
        sut = new LoginService();
    }

    @Test
    void generateResponseReturnsUsernameAndARandomUUID(){
        //assign
        var expectedUserName = "admin";

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