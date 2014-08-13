/*
* Copyright (c) 2005-2010, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
* WSO2 Inc. licenses this file to you under the Apache License,
* Version 2.0 (the "License"); you may not use this file except
* in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied. See the License for the
* specific language governing permissions and limitations
* under the License.
*/

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
 * This class used to get the all key values to set init parameter
 */
public class TomgeryProperties {

    private static final Log LOG = LogFactory.getLog(TomgeryAppChecker.class);

    private InputStream inputStream = null;
    private Properties properties = new Properties();
    private String catalinaPath;

    /**
     * Pointing the jaggery.properties file
     */
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

    /**
     * Get the all keys from jaggery.properties file
     * @return keys
     */
    public Set<Object> getAllKeys(){
        Set<Object> keys = properties.keySet();
        return keys;
    }

    /**
     * Get the value for key from the property file
     * @param key name of the property
     * @return the value of the key
     */
    public String getPropertyValue(String key){
        return this.properties.getProperty(key);
    }

}
