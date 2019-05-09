package com.msdev.trackme.config;

import com.msdev.trackme.security.YAMLConfigHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Profile({"local", "dev"})
@Component
public class StaticFieldInjectionConfig {
    @Autowired
    private Environment env;

    @PostConstruct
    private void init() {
        YAMLConfigHelper.ENV = env.getActiveProfiles();
    }
}
