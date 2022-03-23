package oose.dea.casvansummeren.api.resourses;

import oose.dea.casvansummeren.api.interfaces.ITrackService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackResource {

    private ITrackService trackService;

    @Inject
    public void setTrackService(ITrackService trackService) {
        this.trackService = trackService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetAvailableTracks(@QueryParam("forPlaylist") int playlistId, @QueryParam("token") String token){
        var trackResponse = trackService.getAvailableTracks(playlistId, token);
        return Response
                .status(Response.Status.OK)
                .entity(trackResponse)
                .build();
    }
}
