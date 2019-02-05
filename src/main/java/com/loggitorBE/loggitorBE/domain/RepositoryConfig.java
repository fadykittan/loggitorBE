package com.loggitorBE.loggitorBE.domain;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(App.class);
        config.exposeIdsFor(DefectSeverity.class);
        config.exposeIdsFor(DefinedEvent.class);
        config.exposeIdsFor(EventInstance.class);
        config.exposeIdsFor(EventSeverity.class);
        config.exposeIdsFor(FixAction.class);
    }
}