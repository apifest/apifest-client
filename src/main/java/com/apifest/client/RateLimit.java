package com.apifest.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RateLimit {
    @JsonProperty("requests")
    private Long requests;

    @JsonProperty("per_seconds")
    private Long perSeconds;

    public Long getRequests() {
        return requests;
    }

    public void setRequests(Long requests) {
        this.requests = requests;
    }

    public Long getPerSeconds() {
        return perSeconds;
    }

    public void setPerSeconds(Long perSeconds) {
        this.perSeconds = perSeconds;
    }

    @JsonIgnore
    public boolean isEmpty() {
        if (requests == null || perSeconds == null) {
            return true;
        }
        return false;
    }
}
