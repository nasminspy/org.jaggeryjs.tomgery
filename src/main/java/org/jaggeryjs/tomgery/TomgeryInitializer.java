package org.jaggeryjs.tomgery;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.jaggeryjs.apps.JaggeryContextListener;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * Created by nasmin on 8/4/14.
 */
public class TomgeryInitializer implements ServletContainerInitializer {

    private static final Log LOG = LogFactory.getLog(TomgeryInitializer.class);

    public void onStartup(Set<Class<?>> classes, ServletContext servletContext) throws ServletException {

        String contextPath = servletContext.getContextPath();
        TomgeryAppChecker tomgeryAppChecker = new TomgeryAppChecker();
        boolean isJaggery = tomgeryAppChecker.isJaggeryApp(servletContext);

        if (isJaggery) {

            LOG.info(contextPath + " is a Jaggery Web Application");

            TomgeryProperties tomgeryProperties = new TomgeryProperties();
            Set<Object> tomgeryPropertiesAllKeys = tomgeryProperties.getAllKeys();

            for(Object tomgeryPropertyKey : tomgeryPropertiesAllKeys) {
                String key = (String)tomgeryPropertyKey;
                if(tomgeryProperties.getPropertyValue(key) != null){
                    servletContext.setInitParameter(key, tomgeryProperties.getPropertyValue(key));
                }
            }
            servletContext.addListener(JaggeryContextListener.class.getName());
        } else {

            LOG.info(contextPath + " is NOT a Jaggery Web Application");

        }
    }

}
