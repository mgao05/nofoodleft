package com.molly.service;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public class S3ServiceTest {
//    @Value("${aws.region}")
//    private String region;
    //change vm option so we can change environment variable
    //but we can just comment out and sdk will find region, same concept as withCredentials with vm
    @Test
    public void testS3API(){
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
               // .withRegion(region)
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
        // Upload a text string as a new object.
        s3Client.putObject("mollygao", "randomTest.txt", "Uploaded String Object");
    }
}
