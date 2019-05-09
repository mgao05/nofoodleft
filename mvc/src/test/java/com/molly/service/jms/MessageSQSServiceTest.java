package com.molly.service.jms;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.molly.config.AppConfig;
import org.junit.Test;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class MessageSQSServiceTest {

    @Autowired
    private MessageSQSService messageSQSService;

    @Test
    public void sendMessageTest(){
        messageSQSService.sendMessage("hello world", 5);
    }

//    @Test
//    public void sendMessageTest(){
//        AmazonSQS sqs = AmazonSQSClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).build();
//        SendMessageRequest send_msg_request = new SendMessageRequest()
//                .withQueueUrl("https://sqs.us-east-1.amazonaws.com/920616494522/foodDemo")
//                .withMessageBody("hello world")
//                .withDelaySeconds(5);
//        sqs.sendMessage(send_msg_request);
//
//    }
}
