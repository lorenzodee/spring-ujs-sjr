package springjs.web.servlet.mvc;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private final WebMvcProperties mvcProperties;

	public WebMvcConfig(WebMvcProperties mvcProperties) {
		super();
		this.mvcProperties = mvcProperties;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	}

	// WARNING: Declaring this prevents Spring Boot's MVC auto-configuration
	// to configure the defaultViewResolver bean! Thus, we need to redeclare
	// it here.
	@Bean
	public InternalResourceViewResolver defaultViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix(this.mvcProperties.getView().getPrefix());
		resolver.setSuffix(this.mvcProperties.getView().getSuffix());
		return resolver;
	}

	@Bean
	public InternalResourceViewResolver jsViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix(this.mvcProperties.getView().getPrefix());
		resolver.setSuffix(".js" + this.mvcProperties.getView().getSuffix());
		resolver.setContentType("text/javascript");
		return resolver;
	}

}
