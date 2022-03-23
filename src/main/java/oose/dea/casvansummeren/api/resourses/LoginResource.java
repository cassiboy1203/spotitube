package oose.dea.casvansummeren.api.resourses;

import oose.dea.casvansummeren.exceptions.InvalidLoginException;
import oose.dea.casvansummeren.DTO.LoginDTO;
import oose.dea.casvansummeren.api.interfaces.ILoginService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResource {

    private ILoginService loginService;

    @Inject
    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginDTO login){
        if (!loginService.checkLogin(login.getUser(), login.getPassword())){
            throw new InvalidLoginException();
        }

        var loginResponse = loginService.generateResponse(login.getUser());

        return Response
                .status(Response.Status.CREATED)
                .entity(loginResponse)
                .build();
    }
}
