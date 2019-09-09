package com.vobi.bank.controller;



import javax.jms.JMSException;
import javax.jms.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class MessageController {
	
	private final static Logger log=LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
    private Queue queue;
	
	@Autowired
	private JmsTemplate jmsTemplate;
    
    @GetMapping("message/{message}")
    public ResponseEntity<String> publish(@PathVariable("message") final String message){
    	try {
			log.info(queue.getQueueName().toString());
			jmsTemplate.convertAndSend(queue, message);
		} catch (JMSException e) {
			e.printStackTrace();
		}
       
        return ResponseEntity.ok().body(message);
    }

}
