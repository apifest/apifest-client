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

import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class OAuthClient {
    private String oauthUrl;

    @SuppressWarnings("unused")
    private OAuthClient() {
    }

    public OAuthClient(String oauthUrl) {
        this.oauthUrl = oauthUrl;
    }

    public void registerScope(OAuthScope scope) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(oauthUrl);
        Response response = target.proxy(OAuthServer.class).createScope(scope);
        if (response.getStatus() != 200) {
            throw new RuntimeException(response.readEntity(OAuthScopeResponse.class).getStatus());
        }
    }

    public OAuthApplicationResponse registerApplication(OAuthApplication application) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(oauthUrl);
        Response response = target.proxy(OAuthServer.class).createApplication(application);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Cannot register application " + application.getName());
        }
        return response.readEntity(OAuthApplicationResponse.class);
    }

    public void removeScope(String scopeId) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(oauthUrl);
        Response response = target.proxy(OAuthServer.class).removeScope(scopeId);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Cannot delete scope " + scopeId);
        }
    }

    public void updateApplication(OAuthApplication application, String clientId) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(oauthUrl);
        Response response = target.proxy(OAuthServer.class).updateApplication(clientId, application);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Cannot remove application " + clientId);
        }
    }

    public OAuthTokenResponse fetchToken(TokenRequest tokenRequest) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(oauthUrl);
        Response response = target.proxy(OAuthServer.class).fetchToken(tokenRequest.getGrant_type(),
                                                                       tokenRequest.getScope(),
                                                                       tokenRequest.getClient_id(),
                                                                       tokenRequest.getClient_secret(),
                                                                       tokenRequest.getUsername(),
                                                                       tokenRequest.getPassword());
        if (response.getStatus() != 200 && response.getStatus() != 400) {
            return null;
        }
        return response.readEntity(OAuthTokenResponse.class);
    }

    public String revokeToken(RevokeTokenRequest revokeTokenRequest) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(oauthUrl);
        Response response = target.proxy(OAuthServer.class).revokeToken(revokeTokenRequest);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Cannot revoke access token " + revokeTokenRequest.getAccess_token());
        }
        return response.readEntity(String.class);
    }

    public String revokeUserTokens(RevokeUserTokensRequest revokeUserTokensRequest) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(oauthUrl);
        Response response = target.proxy(OAuthServer.class).revokeUserAccessTokens(revokeUserTokensRequest);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Cannot revoke access tokens for user");
        }
        return response.readEntity(String.class);
    }

}
