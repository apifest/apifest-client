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
