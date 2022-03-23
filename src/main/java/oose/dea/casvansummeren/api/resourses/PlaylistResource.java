package oose.dea.casvansummeren.api.resourses;

import oose.dea.casvansummeren.DTO.PlaylistDTO;
import oose.dea.casvansummeren.DTO.TrackDTO;
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(PlaylistDTO playlist, @QueryParam("token") String token) {
        playlistService.addPlaylist(playlist, token);
        var playlistResponse = playlistService.getPlaylists(token);
        return Response
                .status(Response.Status.CREATED)
                .entity(playlistResponse)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePlaylistName(@PathParam("id") int id, PlaylistDTO playlist, @QueryParam("token") String token) {
        playlistService.updatePlaylistName(id,playlist,token);
        var playlistResponse = playlistService.getPlaylists(token);
        return Response
                .status(Response.Status.OK)
                .entity(playlistResponse)
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        playlistService.deletePlaylist(id, token);
        var playlistsResponse = playlistService.getPlaylists(token);
        return Response
                .status(Response.Status.OK)
                .entity(playlistsResponse)
                .build();
    }

    @POST
    @Path("{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@PathParam("id") int playlistId, TrackDTO track, @QueryParam("token") String token){
        playlistService.addTrackToPlaylist(playlistId, track, token);
        var trackResponse = playlistService.getTracks(playlistId, token);
        return Response
                .status(Response.Status.CREATED)
                .entity(trackResponse)
                .build();
    }

    @DELETE
    @Path("{playlistId}/tracks/{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeTrackFromPlaylist(@PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId, @QueryParam("token") String token){
        playlistService.removeTrackFromPlaylist(playlistId, trackId, token);
        var trackResponse = playlistService.getTracks(playlistId, token);
        return Response
                .status(Response.Status.OK)
                .entity(trackResponse)
                .build();
    }
}
