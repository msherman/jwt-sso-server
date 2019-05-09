package com.msdev.trackme.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class MyTokenEnhancer implements TokenEnhancer {

    private static final Logger log = LoggerFactory.getLogger(MyTokenEnhancer.class);

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        log.debug("enhancing access token {} for {}", accessToken, authentication.getName());

        Object principal = authentication.getPrincipal();

        if (principal instanceof MyUserDetails) {
            accessToken.getAdditionalInformation().put("env", YAMLConfigHelper.ENV);
        } else {
            log.error("a token is being issued to a principal of type " + principal.getClass());
        }

        return accessToken;
    }
}
