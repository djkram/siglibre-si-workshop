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
		
		// TODO Transform messageIn i JSON using mapper
		
		String json = ""; //FIXME
		
		Message<String> messageOut = MessageBuilder.withPayload(json).build();
		
		log.debug("messageOut: " + messageOut.getPayload());
		
		return messageOut;
		
	}
	
	
}
