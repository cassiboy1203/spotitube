package oose.dea.casvansummeren.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidLoginExceptionMapper implements ExceptionMapper<InvalidLoginException> {
    @Override
    public Response toResponse(InvalidLoginException e) {
        return Response
                .status(Response.Status.UNAUTHORIZED)
                .build();
    }
}
