package oose.dea.casvansummeren.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidPermissionExceptionMapper implements ExceptionMapper<InvalidPermissionException> {
    @Override
    public Response toResponse(InvalidPermissionException e) {
        return Response
                .status(Response.Status.FORBIDDEN)
                .build();
    }
}
