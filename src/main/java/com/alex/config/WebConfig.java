package com.alex.config;

import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan("com.alex")
@PropertySource("classpath:/application.properties")
@PropertySource("classpath:/log4j.properties")
@Slf4j
public class WebConfig implements WebMvcConfigurer {



    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setSuffix(".jsp");
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setCache(false);
        viewResolver.setPrefix("/pages/");
        return viewResolver;

    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    @Bean
    public SpringLiquibase liquibase() {
        Context ctx;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("jdbc/myDbDS");
            SpringLiquibase liquibase = new SpringLiquibase();
            liquibase.setChangeLog("classpath:/liquibase/db/changelog-master.xml");
            liquibase.setDataSource(ds);
            return liquibase;
        } catch (NamingException e) {
            log.error("Error creating liquibase bean ", e);
        }
        return null;
    }

}


