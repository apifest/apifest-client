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
    Response fetchToken(@FormParam("grant_type") String grantType,
                        @FormParam("scope") String scope,
                        @FormParam("client_id") String clientId,
                        @FormParam("client_secret") String clientSecret,
                        @FormParam("username") String username,
                        @FormParam("password") String password);

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
