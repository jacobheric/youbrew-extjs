package com.jacobheric.youbrew.utils.json;


import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.beans.factory.InitializingBean;

import java.text.SimpleDateFormat;


/**
 * 
 * Configure default date format.
 *
 */
public class JsonObjectMapper extends ObjectMapper implements InitializingBean {
		
	
	public void afterPropertiesSet() throws Exception {

		//
		// note that EXTJS encodes this as "Y-m-d\\TH:i:s". see metaInfo that specifies dateFormat
		getSerializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
		//
		// note that EXTJS encodes this as "Y-m-d\\TH:i:s". see metaInfo that specifies dateFormat
		getDeserializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));

		// See additional serialization options:
		// http://wiki.fasterxml.com/JacksonFeaturesDeserialization
        getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
	}
    
    
}
