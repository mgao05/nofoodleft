package com.molly.service.jms;


import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.GetQueueUrlRequest;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageSQSService {
    private AmazonSQS sqs;
    private String queueUrl;

//    private AmazonSQS sqs =
//            AmazonSQSClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).build();

//    private String queueUrl = "https://sqs.us-east-1.amazonaws.com/920616494522/foodDemo";
    //public MessageSQSService(@Autowired AmazonSQS sqs){this.sqs = sqs;}


//constructor
    public MessageSQSService(AmazonSQS sqs, String queueUrl){
        this.sqs = sqs;
        this.queueUrl = queueUrl;
    }

    public String getQueueUrl(String queueName){
        GetQueueUrlResult result = sqs.getQueueUrl(queueName);
        String queueUrl = result.getQueueUrl();
        return queueUrl;
    }

    public void sendMessage(String messageBody, int delaySeconds ){
//        AmazonSQS sqs = AmazonSQSClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).build();
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(messageBody)
                .withDelaySeconds(delaySeconds);
        sqs.sendMessage(send_msg_request);
    }


}
