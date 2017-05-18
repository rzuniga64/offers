package org.raulzuniga.offers.config;

import org.raulzuniga.offers.config.WebMvcConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Initializer class.
 */
public class Initializer implements WebApplicationInitializer {

    /**
     * onStartup.
     * @param servletContext servletContext
     * @throws ServletException ServletException
     */
    public void onStartup(final ServletContext servletContext)
            throws ServletException {

        /* Create the 'root' Spring Application context. */
        AnnotationConfigWebApplicationContext mvcContext =
                new AnnotationConfigWebApplicationContext();
        mvcContext.register(WebMvcConfig.class);
        mvcContext.setServletContext(servletContext);
        mvcContext.refresh();

		// Manage the lifecycle of the root application context
		servletContext.addListener(new ContextLoaderListener(mvcContext));

		// Create the dispatcher servlet's Spring application context
		//AnnotationConfigWebApplicationContext dispatcherContext =
				//new AnnotationConfigWebApplicationContext();
		//dispatcherContext.register(DispatcherConfig.class);

        /* Register and map the dispatcher servlet */
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",
                new DispatcherServlet(mvcContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
