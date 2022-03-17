package oose.dea.casvansummeren.business.services;

import oose.dea.casvansummeren.api.interfaces.IAuthService;
import oose.dea.casvansummeren.business.UserDTO;
import oose.dea.casvansummeren.business.interfaces.ILoginDAO;
import oose.dea.casvansummeren.business.services.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    LoginService sut;
    IAuthService mockAuthService;
    ILoginDAO mockLoginDao;

    @BeforeEach
    void setup(){
        sut = new LoginService();

        mockAuthService = Mockito.mock(IAuthService.class);
        mockLoginDao = Mockito.mock(ILoginDAO.class);

        sut.setAuthService(mockAuthService);
        sut.setLoginDAO(mockLoginDao);
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
        var userInfo = new UserDTO();
        userInfo.setId(1);
        userInfo.setName("admin");
        userInfo.setPassword("admin");
        Mockito.when(mockLoginDao.getUser("admin")).thenReturn(userInfo);

        //act
        var actual = sut.checkLogin("admin", "admin");

        //assert
        assertTrue(actual);
    }

    @Test
    void checkUserWrongInfoReturnsFalse(){
        //assign
        var userInfo = new UserDTO();
        userInfo.setPassword("test");
        userInfo.setId(1);
        userInfo.setName("test");
        Mockito.when(mockLoginDao.getUser("test")).thenReturn(userInfo);

        //act
        var actual = sut.checkLogin("test", "test");

        //assert
        assertTrue(actual);
    }
}