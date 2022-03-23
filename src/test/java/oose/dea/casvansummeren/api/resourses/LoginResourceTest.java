package oose.dea.casvansummeren.api.resourses;

import oose.dea.casvansummeren.DTO.LoginDTO;
import oose.dea.casvansummeren.DTO.LoginResponseDTO;
import oose.dea.casvansummeren.api.interfaces.ILoginService;
import oose.dea.casvansummeren.exceptions.InvalidLoginException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LoginResourceTest {

    private static final String USER_NAME_DUMMY = "admin";
    private static final String PASSWORD_DUMMY = "admin";
    private static final String TOKEN_DUMMY = "1234-1234-1234";

    private LoginResource sut;
    private ILoginService mockLoginService;

    @BeforeEach
    void setup(){
        sut = new LoginResource();
        mockLoginService = Mockito.mock(ILoginService.class);

        sut.setLoginService(mockLoginService);
    }

    @Test
    void LoginAsAdminTest(){
        //assign
        var input  = new LoginDTO();
        input.setUser(USER_NAME_DUMMY);
        input.setPassword(PASSWORD_DUMMY);

        var expected = new LoginResponseDTO();
        expected.setToken(TOKEN_DUMMY);
        expected.setUser(USER_NAME_DUMMY);

        Mockito.when(mockLoginService.generateResponse(USER_NAME_DUMMY)).thenReturn(expected);
        Mockito.when(mockLoginService.checkLogin(USER_NAME_DUMMY,PASSWORD_DUMMY)).thenReturn(true);

        var expectedStatus = Response.status(Response.Status.CREATED).build().getStatus();

        //act
        var actualResponse = sut.login(input);
        var actualEntity = (LoginResponseDTO)actualResponse.getEntity();

        //assert
        assertEquals(expected, actualEntity);
        assertEquals(expectedStatus, actualResponse.getStatus());
    }

    @Test
    void InvalidUserName(){
        //assign
        var input = new LoginDTO();
        input.setUser(USER_NAME_DUMMY);
        input.setPassword(PASSWORD_DUMMY);

        var expected = 401;

        Mockito.when(mockLoginService.checkLogin(USER_NAME_DUMMY,PASSWORD_DUMMY)).thenReturn(false);

        //assert
        assertThrows(InvalidLoginException.class, () -> {
            sut.login(input).getStatus();
        });
    }
}