package springjs.web.servlet.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private WebMvcProperties mvcProperties;
	@Autowired
	private ContentNegotiationManager contentNegotiationManager;

	/**
	 * Default view resolver mapped to <code>*.html.jsp</code>. This replaces Spring
	 * Boot's MVC auto-configuration default view resolver.
	 */
	@Bean
	public InternalResourceViewResolver defaultViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setRedirectHttp10Compatible(false);
		resolver.setPrefix(this.mvcProperties.getView().getPrefix());
		resolver.setSuffix(".html" + this.mvcProperties.getView().getSuffix());
		resolver.setContentType("text/html");
		return resolver;
	}

	/**
	 * Support server-generated JavaScript responses (SJRs) via
	 * <code>*.js.jsp</code>.
	 */
	@Bean
	public InternalResourceViewResolver jsViewResolver() {
		InternalResourceViewResolver jsViewResolver = new InternalResourceViewResolver();
		jsViewResolver.setRedirectHttp10Compatible(false);
		jsViewResolver.setPrefix(this.mvcProperties.getView().getPrefix());
		jsViewResolver.setSuffix(".js" + this.mvcProperties.getView().getSuffix());
		jsViewResolver.setContentType("text/javascript");
		jsViewResolver.setOrder(Ordered.LOWEST_PRECEDENCE - 10); // precedes default view resolver
		return jsViewResolver;
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(
				new RequestedMediaTypesMethodArgumentResolver(
						this.contentNegotiationManager));
	}

}
