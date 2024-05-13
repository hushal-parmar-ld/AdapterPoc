package com.example.demo_ibm_mq.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IBMMQController {
	
	@Value("${project.mq.queue-name}")
    private String queueName;
	
	@Autowired
	private JmsOperations jmsOperations;
	
	@PostMapping("/send")
    public String send(){
		String message = "Hello KALI DAL!!!";
		jmsOperations.convertAndSend(queueName,message );
		System.out.println("message sent :"+message);
        return "Send message successfully";
    }
}
