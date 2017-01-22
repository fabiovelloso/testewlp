/*******************************************************************************
 * Copyright (c) 2014 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package net.wasdev.samples.ferret;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public final class FerretData {

    private final String requestedUrl;
    private final String servletName;
    private final RequestData requestData;
    private final Map<String, String> servletContextAttributes;

    public FerretData(final ServletConfig servletConfig, final ServletContext servletContext,
            final HttpServletRequest httpServletRequest) {
        requestedUrl = httpServletRequest.getRequestURL().toString();
        servletName = servletConfig.getServletName();
        requestData = new RequestData(httpServletRequest);

        servletContextAttributes = getServletContextAttributes(servletContext);
    }

    private Map<String, String> getServletContextAttributes(final ServletContext servletContext) {
        final List<String> attributeNames = Collections.list(servletContext.getAttributeNames());
        final Map<String, String> attributes = new HashMap<String, String>();
        for (final String name : attributeNames) {
            final Object attribute = servletContext.getAttribute(name);
            if (attribute != null) {
                attributes.put(name, attribute.toString());
            } else {
                attributes.put(name, "");
            }
        }
        return attributes;
    }

    public String getRequestedUrl() {
        return requestedUrl;
    }

    public String getServletName() {
        return servletName;
    }

    public RequestData getRequestData() {
        return requestData;
    }

    public Map<String, String> getServletContextAttributes() {
        return servletContextAttributes;
    }

    @Override
    public String toString() {
        return "<p>requestedUrl = " + requestedUrl + "</p> servletName = " + servletName + "</p> requestData = "
                + requestData + "</p> servletContextAttributes = " + servletContextAttributes;
    }

    public Map<String, Object> getAsMap() {
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("requestedUrl", requestedUrl);
        map.put("servletName", servletName);
        map.put("requestData", requestData.getAsMap());
        map.put("servletContextAttributes", servletContextAttributes);
        return map;
    }
    
}
