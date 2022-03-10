package oose.dea.casvansummeren.api.resourses;

import oose.dea.casvansummeren.api.interfaces.IPlaylistService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {

    private IPlaylistService playlistService;

    @Inject
    public void setPlaylistService(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token){
        var playlistsResponse = playlistService.getPlaylists(token);
        return Response
                .status(Response.Status.OK)
                .entity(playlistsResponse)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/tracks")
    public Response getTracks(@PathParam("id") int id, @QueryParam("token") String token){
        var playlistResponse = playlistService.getTracks(id,token);
        return Response
                .status(Response.Status.OK)
                .entity(playlistResponse)
                .build();
    }
}
