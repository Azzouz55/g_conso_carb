package com.pfe.gestioncarburant.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class ApplicationInitializer extends
		AbstractSecurityWebApplicationInitializer {

	public ApplicationInitializer() {
        super(AppConfig.class);
    }
}