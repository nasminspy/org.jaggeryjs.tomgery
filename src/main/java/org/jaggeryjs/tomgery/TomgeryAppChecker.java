package org.jaggeryjs.tomgery;

import javax.servlet.ServletContext;
import java.io.File;

/**
 * Created by nasmin on 8/3/14.
 */
public class TomgeryAppChecker {

        public boolean isJaggeryApp(ServletContext servletContext) {

        String contextPath = servletContext.getContextPath();
        String catalinaPath = System.getProperty(TomgeryConstants.CATALINA_BASE);

        File file = new File(catalinaPath + File.separator + TomgeryConstants.WEB_APPS + contextPath + File.separator + TomgeryConstants.JAGGERY_CONF );

        return file.exists();
    }

}
