package com.apifest.client;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/oauth20")
public interface OAuthServer {

    @Path("/scopes")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response createScope(OAuthScope scope);

    @Path("/scopes/{scopeId}")
    @DELETE
    Response removeScope(@PathParam("scopeId") String scopeId);

    @Path("/applications")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response createApplication(OAuthApplication application);

    @Path("/applications/{applicationId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response updateApplication(@PathParam("applicationId") String applicationId, OAuthApplication application);

    @Path("/tokens")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    Response fetchApplicationToken(@FormParam("grant_type") String grantType,
                                   @FormParam("scope") String scope,
                                   @FormParam("client_id") String clientId,
                                   @FormParam("client_secret") String clientSecret);

}
