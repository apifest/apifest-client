package com.apifest.client;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class OAuthClient {
    private String oauthUrl;

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

    public String fetchApplicationToken(String initialScope, String clientId, String clientSecret) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(oauthUrl);
        Response response = target.proxy(OAuthServer.class).fetchApplicationToken("client_credentials", initialScope,
                clientId, clientSecret);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Cannot fetch application token " + response.readEntity(String.class));
        }
        OAuthTokenResponse tokenResponse = response.readEntity(OAuthTokenResponse.class);
        return tokenResponse.getAccess_token();
    }
}
