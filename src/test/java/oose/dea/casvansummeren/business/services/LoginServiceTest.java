package oose.dea.casvansummeren.business.services;

import oose.dea.casvansummeren.api.interfaces.IAuthService;
import oose.dea.casvansummeren.DTO.UserDTO;
import oose.dea.casvansummeren.business.interfaces.ILoginDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    private static final String USER_NAME_DUMMY = "admin";
    private static final String TOKEN_DUMMY = "1234-1234-1234";
    private static final String PASSWORD_DUMMY = "admin";

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
        var expectedUserName = USER_NAME_DUMMY;
        Mockito.when(mockAuthService.generateToken()).thenReturn(TOKEN_DUMMY);

        //act
        var actual = sut.generateResponse(USER_NAME_DUMMY);

        //assert
        assertEquals(expectedUserName, actual.getUser());
        assertEquals(TOKEN_DUMMY, actual.getToken());
    }

    @Test
    void checkUserCorrectInfoReturnsTrue(){
        //assign
        var userInfo = new UserDTO();
        userInfo.setId(1);
        userInfo.setName(USER_NAME_DUMMY);
        userInfo.setPassword(PASSWORD_DUMMY);
        Mockito.when(mockLoginDao.getUser(USER_NAME_DUMMY)).thenReturn(userInfo);

        //act
        var actual = sut.checkLogin(USER_NAME_DUMMY, PASSWORD_DUMMY);

        //assert
        assertTrue(actual);
    }

    @Test
    void checkUserWrongInfoReturnsFalse(){
        //assign
        var userInfo = new UserDTO();
        userInfo.setPassword(PASSWORD_DUMMY);
        userInfo.setId(1);
        userInfo.setName(USER_NAME_DUMMY);
        Mockito.when(mockLoginDao.getUser(USER_NAME_DUMMY)).thenReturn(userInfo);

        //act
        var actual = sut.checkLogin(USER_NAME_DUMMY, PASSWORD_DUMMY);

        //assert
        assertTrue(actual);
    }
}