package com.example.demo_ibm_mq.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;


@Service
public class TransformationService {

    public String performTransformation(String messageData, String xsltFilePath) {
        try {
            // Load XSLT file
            File xsltFile = new File(xsltFilePath);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            // Perform transformation
            StringWriter resultWriter = new StringWriter();
            transformer.transform(new StreamSource(new StringReader(messageData)), new StreamResult(resultWriter));
            System.out.println("messageData :"+messageData);
            System.out.println("transformed :"+resultWriter);

            return resultWriter.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
