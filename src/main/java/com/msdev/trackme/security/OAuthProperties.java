package com.msdev.trackme.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "my.oauth2")
public class OAuthProperties {

    private List<Client> clients = new ArrayList<>();

    public static class Client {
        private String name;
        private String key;
        private String secret;
        private List<String> redirectPrefix;
        private GrantType grantType = GrantType.USER;


        public Client() {}

        public Client(String name, String key, List<String> redirectPrefix) {
            this.name = name;
            this.key = key;
            this.redirectPrefix = redirectPrefix;
        }

        public Client(String name, String key, String secret, List<String> redirectPrefix, GrantType grantType) {
            this.name = name;
            this.key = key;
            this.secret = secret;
            this.redirectPrefix = redirectPrefix;
            this.grantType = grantType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public List<String> getRedirectPrefix() {
            return redirectPrefix;
        }

        public void setRedirectPrefix(List<String> redirectPrefix) {
            this.redirectPrefix = redirectPrefix;
        }

        public GrantType getGrantType() {
            return grantType;
        }

        public void setGrantType(GrantType grantType) {
            this.grantType = grantType;
        }

        public enum GrantType {
            USER, CLIENT
        }

    }

    public List<Client> getClients() {
        return clients;
    }
}
