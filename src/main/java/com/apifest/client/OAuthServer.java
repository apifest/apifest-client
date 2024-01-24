/*
 * Copyright 2013-2015, ApiFest project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.apifest.client;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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

    @Path("/applications/{clientId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    Response getApplication(@PathParam("clientId") String applicationId);

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
    Response fetchToken(@FormParam("grant_type") String grantType,
                        @FormParam("scope") String scope,
                        @FormParam("client_id") String clientId,
                        @FormParam("client_secret") String clientSecret,
                        @FormParam("username") String username,
                        @FormParam("password") String password,
                        @FormParam("refresh_token") String refreshToken);

    @Path("/tokens/revoke")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response revokeToken(RevokeTokenRequest revokeTokenRequest);

    @Path("/tokens")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response revokeUserAccessTokens(RevokeUserTokensRequest revokeUserTokensRequest);

}
