package springjs.web.servlet.mvc;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.RedirectView;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private WebMvcProperties mvcProperties;

	/**
	 * Default view resolver mapped to <code>*.html.jsp</code>. This prevents Spring
	 * Boot's MVC auto-configuration from configuring a default view resolver.
	 */
	@Bean
	public InternalResourceViewResolver defaultViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver() {
			@Override
			protected View createView(String viewName, Locale locale) throws Exception {
				View view = super.createView(viewName, locale);
				if (view instanceof RedirectView) {
					if (getContentType() != null) {
						((RedirectView) view).setContentType(getContentType());
					}
				}
				return view;
			}
		};
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
		// resolveViewName -> createView -> loadView -> buildView
		InternalResourceViewResolver resolver = new InternalResourceViewResolver() {
			@Override
			protected View createView(String viewName, Locale locale) throws Exception {
				if (!canHandle(viewName, locale)) {
					return null;
				}
				// Check for special "redirect:" prefix.
				if (viewName.startsWith(REDIRECT_URL_PREFIX)) {
					String redirectUrl = viewName.substring(REDIRECT_URL_PREFIX.length());
					RedirectView view = new JsRedirectView(redirectUrl,
							isRedirectContextRelative(), isRedirectHttp10Compatible());
					if (getContentType() != null) {
						view.setContentType(getContentType());
					}
					String[] hosts = getRedirectHosts();
					if (hosts != null) {
						view.setHosts(hosts);
					}
					return applyLifecycleMethods(REDIRECT_URL_PREFIX, view);
				}
				return super.createView(viewName, locale);
			}
		};
		resolver.setRedirectHttp10Compatible(false);
		resolver.setPrefix(this.mvcProperties.getView().getPrefix());
		resolver.setSuffix(".js" + this.mvcProperties.getView().getSuffix());
		resolver.setContentType("text/javascript");
		resolver.setOrder(Ordered.LOWEST_PRECEDENCE - 10); // precedes default view resolver
		return resolver;
	}

	/**
	 * A {@link RedirectView} that redirects using JavaScript.
	 *
	 * @author Lorenzo Dee
	 * @see #sendRedirect(HttpServletRequest, HttpServletResponse, String, boolean)
	 */
	public static class JsRedirectView extends RedirectView {

		public JsRedirectView(String url, boolean contextRelative, boolean http10Compatible) {
			super(url, contextRelative, http10Compatible);
		}

		@Override
		protected void sendRedirect(HttpServletRequest request, HttpServletResponse response,
				String targetUrl, boolean http10Compatible) throws IOException {
			// TODO Allow the presence of *.js.jsp to override
			// E.g. given /articles/{id} as path, and update as method, then "articles/update.js" as view name
			// - URI template variables removed
			// - Method name used as view name
			// - If view does not exist, then default to redirect via JS
			String encodedURL = (isRemoteHost(targetUrl) ? targetUrl : response.encodeRedirectURL(targetUrl));
			response.setContentType("text/javascript");
			response.getWriter().write("window.location.replace('" + encodedURL + "')");
		}

	}

}
