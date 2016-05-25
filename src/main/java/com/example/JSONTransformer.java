package com.example;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import com.fasterxml.jackson.databind.ObjectMapper;

import twitter4j.GeoLocation;
import twitter4j.Status;

public class JSONTransformer {

	private Log log = LogFactory.getLog(JSONTransformer.class);
	
	public Message<String> transformtoJSON(Message<Status> messageIn) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		Status tweet = messageIn.getPayload();
		
		GeoLocation geo = tweet.getGeoLocation();
		
		String result;
		
//		if (geo == null){
//			result = "No GeoLocation";
//		}else{
//			result = geo.toString();
//		}
		
		String json = mapper.writeValueAsString(tweet);
		
		Message<String> messageOut = MessageBuilder.withPayload(json).build();
		
		log.debug("messageOut: " + messageOut.getPayload());
		
		return messageOut;
		
	}
	
	
}
