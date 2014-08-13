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
import org.jaggeryjs.apps.JaggeryAsyncServlet;
import org.jaggeryjs.apps.JaggeryContextListener;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

/**
 * SCI class registered via META-INF services to deploy jaggery app
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

            //get property values and set context parameter
            for(Object tomgeryPropertyKey : tomgeryPropertiesAllKeys) {
                String key = (String)tomgeryPropertyKey;
                if(tomgeryProperties.getPropertyValue(key) != null){
                    servletContext.setInitParameter(key, tomgeryProperties.getPropertyValue(key));
                }
            }

            //register the jaggery context listener on start event of the context
            servletContext.addListener(JaggeryContextListener.class.getName());

            //register the jaggery async servlet and add mapping
            ServletRegistration.Dynamic registration = servletContext.addServlet(
                    JaggeryAsyncServlet.NAME, JaggeryAsyncServlet.class);
            registration.setAsyncSupported(true);
            registration.addMapping("/*");

            } else {
            LOG.info(contextPath + " is NOT a Jaggery Web Application");
        }
    }

}
