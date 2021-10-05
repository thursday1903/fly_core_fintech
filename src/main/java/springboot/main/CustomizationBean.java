package springboot.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import springboot.config.YAMLConfig;

@Component
public class CustomizationBean implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

	@Autowired
	private YAMLConfig myConfig;

	@Override
	public void customize(ConfigurableServletWebServerFactory container) {
		container.setPort(myConfig.getListen_port());
	}

	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>
	  webServerFactoryCustomizer() {
	    return factory -> factory.setContextPath(myConfig.getContext_root());
	}
}
