package com.topshow.configuration;

import com.topshow.configuration.WebConfig;
import com.topshow.interceptor.AuthInterceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@ComponentScan(basePackages = { "com.topshow.*" }, useDefaultFilters = false, includeFilters = {
        @Filter(type = FilterType.ANNOTATION, classes = { Controller.class,ControllerAdvice.class }) })
@EnableAspectJAutoProxy
public class WebConfig extends WebMvcConfigurationSupport {
    private static Logger logger = LogManager.getLogger(WebConfig.class.getName());

    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(new String[] { "/static/css/**", "/static/fonts/**", "/static/img/**", "/static/js/**", "/video/**" })
                .addResourceLocations(new String[] { "/static/css/", "/static/fonts/", "/static/img/", "/static/js/", "/static/video/" });
    }

    protected void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/404").setViewName("/errors/404");
        registry.addViewController("/500").setViewName("/errors/500");
        registry.addViewController("/403").setViewName("/errors/403");
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine springTemplateEngine) {
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(springTemplateEngine);
        thymeleafViewResolver.setCharacterEncoding("utf-8");
        logger.info("thymeleaf 视图解析器------> success create");
        return thymeleafViewResolver;
    }

    @Bean(name = { "springTemplateEngine" })
    public SpringTemplateEngine springTemplateEngine(SpringResourceTemplateResolver springResourceTemplateResolver) {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(springResourceTemplateResolver);
        springTemplateEngine.addDialect(new SpringSecurityDialect());
        logger.info("SpringTemplateEngine 模板引擎------> success create");
        return springTemplateEngine;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/view/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(false);
        templateResolver.setCharacterEncoding("utf-8");
        templateResolver.setOrder(Integer.valueOf(0));
        logger.info("SpringResourceTemplateResolver 模板解析器------> success create");
        return templateResolver;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        commonsMultipartResolver.setMaxUploadSizePerFile(6*1024*1024*1024L);
        commonsMultipartResolver.setResolveLazily(true);
        return commonsMultipartResolver;
    }

    protected void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new AuthInterceptor());
        interceptorRegistration.excludePathPatterns(new String[] { "/admin/login", "/admin/regist","/admin/login/submit" });
        interceptorRegistration
                .addPathPatterns(new String[] { "/admin/**"});
        super.addInterceptors(registry);
    }
}
