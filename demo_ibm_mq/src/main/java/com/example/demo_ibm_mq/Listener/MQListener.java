package com.example.demo_ibm_mq.Listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import com.example.demo_ibm_mq.Service.TransformationService;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Component
public class MQListener {

	private final TransformationService transformationService;

	@Autowired
	public MQListener(TransformationService transformationService) {
		this.transformationService = transformationService;
	}

	@JmsListener(destination = "${project.mq.queue-name}")
	public void receiveMessage(final Message jsonMessage) throws JMSException {
		if (jsonMessage instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) jsonMessage;
			String messageData = textMessage.getText();
			System.out.println("Received: " + messageData);

			// Perform transformation
			String xsltFilePath = "src/main/resources/transform.xslt"; // Update with your actual file path
			String transformedMessage = transformationService.performTransformation(messageData, xsltFilePath);
			System.out.println("Transformed: " + transformedMessage);
		}
	}
}
