package com.topshow.configuration;

import com.topshow.configuration.WebAppInitializer;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    private static Logger logger = LogManager.getLogger(WebAppInitializer.class.getName());

    protected Class<?>[] getRootConfigClasses() {
        logger.info("root配置类初始化---->");

        return new Class[] { RootConfig.class };
    }

    protected Class<?>[] getServletConfigClasses() {
        logger.info("web配置类初始化---->");

        return new Class[] { WebConfig.class };
    }

    protected String[] getServletMappings() {
        logger.info("映射路径初始化---->");

        return new String[] { "/" };
    }

    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("utf-8");
        characterEncodingFilter.setForceEncoding(true);
        logger.info("CharacterEncodingFilter---->");
        return new Filter[] { characterEncodingFilter };
    }

    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter",CharacterEncodingFilter.class);
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.setAsyncSupported(true);
        encodingFilter.addMappingForUrlPatterns(null, false, new String[] { "/*" });
    }
}
