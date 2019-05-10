package com.molly.service.jms;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.molly.config.AppConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verify;

@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class MessageSQSServiceTest {

    @Autowired
    private MessageSQSService messageSQSService;

    @Mock
    private AmazonSQS amazonSQS = Mockito.mock(AmazonSQS.class);

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws  Exception{
        validateMockitoUsage();

    }

    @Test
    public void sendMessageTest(){
        messageSQSService.sendMessage("Molly",1);
        verify(amazonSQS).sendMessage(any());

    }


//    @Test
//    public void sendMessageTest(){
//        messageSQSService.sendMessage("hello world", 5);
//    }

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
