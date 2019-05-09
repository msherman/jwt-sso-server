package com.msdev.trackme.security;

import org.springframework.security.core.GrantedAuthority;
import com.google.common.collect.ImmutableSet;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class MyClientDetails implements ClientDetails {

    private static final long serialVersionUID = 1l;
    private static final Set<String> CODE_GRANT = ImmutableSet.of("authorization_code", "refresh_token");
    private static final Set<String> CLIENT_GRANT = ImmutableSet.of("authorization_code", "client_credentials");
    private static final Set<String> CLIENT_SCOPES = ImmutableSet.of("users.read");

    private final String clientId;

    private final String clientSecret;

    private final Set<String> registeredRedirectUri;

    private final Set<String> authorizedGrantTypes;

    private final Set<String> scopes;

    MyClientDetails(final OAuthProperties.Client client) {
        this.clientId = client.getKey();
        this.clientSecret = client.getSecret();
        this.registeredRedirectUri = ImmutableSet.copyOf(client.getRedirectPrefix());

        if (OAuthProperties.Client.GrantType.CLIENT.equals(client.getGrantType())) {
            this.authorizedGrantTypes = CLIENT_GRANT;
            this.scopes = CLIENT_SCOPES;
        } else {
            this.authorizedGrantTypes = CODE_GRANT;
            this.scopes = Collections.emptySet();
        }
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return Collections.emptySet();
    }

    @Override
    public boolean isSecretRequired() {
        return this.clientSecret != null;
    }

    @Override
    public String getClientSecret() {
        return this.clientSecret;
    }

    @Override
    public boolean isScoped() {
        return !this.scopes.isEmpty();
    }

    @Override
    public Set<String> getScope() {
        return this.scopes;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return registeredRedirectUri;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Collections.emptySet();
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return null;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return null;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return true;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return Collections.emptyMap();
    }

    @Override
    public String toString() {
        return "MyClientDetails[" + clientId + "]";
    }



}
