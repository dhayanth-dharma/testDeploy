package com.example.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api2")
public class TestTwoController {

	 @Autowired
	    private QueueMessagingTemplate queueMessagingTemplate;

	    @Value("${cloud.aws.end-point.uri}")
	    private String endpoint;

	    @GetMapping("/send/{message}")
	    public String sendMessageToQueue(@PathVariable String message) {
	        try {
	        	queueMessagingTemplate.send(endpoint, MessageBuilder.withPayload(message).build());
		        return "OK";	
	        }catch(Exception ex)
	        {
	        	return ex.getMessage();
	        }
	    	
	    }
}
