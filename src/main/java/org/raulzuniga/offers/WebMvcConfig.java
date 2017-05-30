package org.raulzuniga.offers;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;

/**
 * WebMvcConfig class.
 */
@Configuration
@EnableWebMvc
@ComponentScan
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     *  To configure your own DataSource define a @Bean of that type in your
     *  configuration. Spring Boot will reuse your DataSource anywhere one is
     *  required, including database initialization.
     */
    @Bean
    @ConfigurationProperties("spring.datasource")
    public static DataSource dataSource() {

        ClassPathXmlApplicationContext ctx
                = new ClassPathXmlApplicationContext("datasource.xml");
        return (DataSource) ctx.getBean("dataSource");
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages-en-US");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    /**
     *  Add resource handler.
     *  @param registry registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    /**
     *  Configure the view resolver.
     *  @param registry view resolver registry
     */
    @Override
    public void configureViewResolvers(final ViewResolverRegistry registry) {

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsps/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        registry.viewResolver(resolver);
    }
}
