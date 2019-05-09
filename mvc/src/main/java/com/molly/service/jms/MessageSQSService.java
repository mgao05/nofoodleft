package com.molly.service.jms;


import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageSQSService {
    private AmazonSQS sqs;
    private String queueUrl;
    //public MessageSQSService(@Autowired AmazonSQS sqs){this.sqs = sqs;}
//constructor
    public MessageSQSService(AmazonSQS sqs, String queueUrl){
        this.sqs = sqs;
        this.queueUrl = queueUrl;
    }

    public void sendMessage(String messageBody, int delaySeconds ){
        AmazonSQS sqs = AmazonSQSClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).build();
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl("https://sqs.us-east-1.amazonaws.com/920616494522/foodDemo")
                .withMessageBody(messageBody)
                .withDelaySeconds(delaySeconds);
        sqs.sendMessage(send_msg_request);
    }


}
