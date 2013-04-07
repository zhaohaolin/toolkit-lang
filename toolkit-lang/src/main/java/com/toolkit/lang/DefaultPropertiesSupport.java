/*******************************************************************************
 * CopyRight (c) 2005-2011 GLOBE Co, Ltd. All rights reserved.
 * Filename:    DefaultPropertiesSupport.java
 * Creator:     joe
 * Create-Date: 2011-4-28 上午09:22:32
 *******************************************************************************/
package com.toolkit.lang;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * TODO
 * 
 * @author joe
 * @version $Id: DefaultPropertiesSupport.java 59 2011-04-28 01:21:12Z joe $
 */
public class DefaultPropertiesSupport implements Propertyable, Cloneable {

    private Map<String, Object> properties = new HashMap<String, Object>();

    public Object getProperty(String key) {
        return properties.get(key);
    }

    public Map<String, Object> getProperties() {
        return Collections.unmodifiableMap(properties);
    }

    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties.clear();

        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            if (null != entry.getValue()) {
                this.properties.put(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public DefaultPropertiesSupport clone() throws CloneNotSupportedException {
        DefaultPropertiesSupport o = (DefaultPropertiesSupport) super.clone();

        o.setProperties(this.properties);
        return o;
    }

    public String toString() {

        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((properties == null) ? 0 : properties.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DefaultPropertiesSupport other = (DefaultPropertiesSupport) obj;
        if (properties == null) {
            if (other.properties != null)
                return false;
        } else if (!properties.equals(other.properties))
            return false;
        return true;
    }
}
