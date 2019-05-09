package com.molly.service;

import com.amazonaws.services.s3.AmazonS3;
import com.molly.config.AppConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import com.molly.service.StorageService;

import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class StorageServiceTest{

    @InjectMocks
    @Autowired
    private StorageService storageService;

    @Value("#{ databaseProperties['amazon.s3.bucket']}")
    protected String s3Bucket;

    @Mock
    private AmazonS3 client= Mockito.mock(AmazonS3.class);
    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }
    @After
    public void tearDown() throws Exception{
        validateMockitoUsage();
    }

    @Test
    public void getObjectTest(){
        String key = "testKey";
        storageService.getObject(key);
        //here it is to test run times of getObject in AmazonS3 interface,
        verify(client, times(1)).getObject(s3Bucket, key);
        String key2 = null;
        storageService.getObject(key2);
        verify(client, times(1)).getObject(s3Bucket, key);

    }




}
