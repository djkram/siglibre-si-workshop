package com.example;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import twitter4j.Status;

public class TwitterGeoInboundAdapter {

	private Log log = LogFactory.getLog(TwitterGeoInboundAdapter.class);
	
	@Autowired
	private TwitterStreamService twitterStreamService;
	
	public Message<?> receive() throws IOException{
		
		// TODO get tweet from twitterStreamService
		
		Object tweet = null; //FIXME
		
		Message<?> messageIn = MessageBuilder.withPayload(tweet).build();
		log.debug("messageIn: " + messageIn.getPayload());
		return messageIn;
		
	}
	
	
}
