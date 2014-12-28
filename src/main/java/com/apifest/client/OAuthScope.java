package com.apifest.client;

public class OAuthScope {

    String scope;
    String description;
    Long cc_expires_in;
    Long pass_expires_in;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCc_expires_in() {
        return cc_expires_in;
    }

    public void setCc_expires_in(Long cc_expires_in) {
        this.cc_expires_in = cc_expires_in;
    }

    public Long getPass_expires_in() {
        return pass_expires_in;
    }

    public void setPass_expires_in(Long pass_expires_in) {
        this.pass_expires_in = pass_expires_in;
    }

}
