package com.msdev.trackme.security;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties(OAuthProperties.class)
public class MyClientDetailsService implements ClientDetailsService {

    private final OAuthProperties OAuthProperties;

    public MyClientDetailsService(OAuthProperties OAuthProperties) {
        this.OAuthProperties = OAuthProperties;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return OAuthProperties.getClients().stream()
                .filter(c -> c.getKey().equals(clientId))
                .findFirst()
                .map(MyClientDetails::new)
                .orElseThrow(() -> new NoSuchClientException("no client with key " + clientId));
    }

}
