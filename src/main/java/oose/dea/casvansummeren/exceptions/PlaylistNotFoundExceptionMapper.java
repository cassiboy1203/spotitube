package oose.dea.casvansummeren.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PlaylistNotFoundExceptionMapper implements ExceptionMapper<PlaylistNotFoundException> {
    @Override
    public Response toResponse(PlaylistNotFoundException e) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }
}
