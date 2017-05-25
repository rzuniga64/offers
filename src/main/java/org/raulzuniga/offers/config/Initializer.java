package org.raulzuniga.offers.config;

import org.raulzuniga.offers.WebMvcConfig;
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

        AnnotationConfigWebApplicationContext context = getContext();
        context.setServletContext(servletContext);
        context.refresh();

		// Manage the lifecycle of the root application context
        // Spring needs to load our config file. -->
		servletContext.addListener(new ContextLoaderListener(context));

        /* Register and map the dispatcher servlet */
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",
                new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/*");
    }

    /**
     *  Create the 'root' Spring Application context.
     *  @return the context
     */
    private AnnotationConfigWebApplicationContext getContext() {

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebMvcConfig.class);

        return context;
    }
}
