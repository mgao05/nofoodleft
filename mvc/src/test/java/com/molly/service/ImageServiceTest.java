package com.molly.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.molly.config.AppConfig;
import com.molly.domain.Image;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.when;

@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})

public class ImageServiceTest {
    @InjectMocks
    @Autowired
    private StorageService storageService;
    @Mock
    private AmazonS3 client = Mockito.mock(AmazonS3.class);
    @Autowired
    private ImageService imageService;

    @Value("#{databaseProperties['amazon.s3.bucket']}")
    private String bucket;

    @Before

    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        validateMockitoUsage();
    }

    @Test
    public void saveImageTest() throws Exception{
        MultipartFile multipartFile = Mockito.mock(CommonsMultipartFile.class);
        String key = "fake";
        String urlString = "http://api/"+key+".png";
        URL fakeUrl = Mockito.mock(URL.class);
        when(fakeUrl.toString()).thenReturn(urlString);
        when(client.getUrl(bucket,key)).thenReturn(fakeUrl);
        when(multipartFile.getOriginalFilename()).thenReturn(urlString);
        S3Object s3Object = Mockito.mock(S3Object.class);
        when(s3Object.getKey()).thenReturn(key);
        when(storageService.getObjectUrl(key)).thenReturn(urlString);
        Image testImage = imageService.saveImage(multipartFile,true);
        assertNotNull(testImage.getUrl());
        assertEquals(testImage.getUrl(),fakeUrl.toString());


    }


}
