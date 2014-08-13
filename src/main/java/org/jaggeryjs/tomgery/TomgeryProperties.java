package org.jaggeryjs.tomgery;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

/**
 * Created by nasmin on 8/3/14.
 */
public class TomgeryProperties {

    private static final Log LOG = LogFactory.getLog(TomgeryAppChecker.class);

    private InputStream inputStream = null;
    private Properties properties = new Properties();
    private String catalinaPath;

    public TomgeryProperties() {
        catalinaPath = System.getProperty(TomgeryConstants.CATALINA_BASE);
        try {
            inputStream = new FileInputStream(catalinaPath + File.separator + TomgeryConstants.JAGGERY_HOME + File.separator + TomgeryConstants.JAGGERY_PROPERTIES );
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            LOG.error("Error in reading the property file", e);
        } catch (IOException e) {
            LOG.error("Error in loading the property file", e);
        }
    }

    public Set<Object> getAllKeys(){
        Set<Object> keys = properties.keySet();
        return keys;
    }

    public String getPropertyValue(String key){
        return this.properties.getProperty(key);
    }

}
