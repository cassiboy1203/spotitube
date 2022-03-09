package oose.dea.casvansummeren.api;

import oose.dea.casvansummeren.exceptions.InvalidLoginException;
import oose.dea.casvansummeren.api.resourses.LoginResource;
import oose.dea.casvansummeren.api.DTO.LoginDTO;
import oose.dea.casvansummeren.api.DTO.LoginResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

class LoginResourceTest {

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
        input.setUser("admin");
        input.setPassword("admin");

        var expected = new LoginResponseDTO();
        expected.setToken("1234-1234-1234");
        expected.setUser("admin");

        Mockito.when(mockLoginService.generateResponse("admin")).thenReturn(expected);
        Mockito.when(mockLoginService.checkLogin("admin","admin")).thenReturn(true);

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
        input.setUser("test");
        input.setPassword("test");

        var expected = 401;

        Mockito.when(mockLoginService.checkLogin("test","test")).thenReturn(false);

        //assert
        assertThrows(InvalidLoginException.class, () -> {
            sut.login(input).getStatus();
        });
    }
}