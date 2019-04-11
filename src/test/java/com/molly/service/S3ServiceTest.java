package com.molly.service;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.junit.Test;

import java.io.IOException;

public class S3ServiceTest {
    @Test
    public void testS3API(){
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion("us-east-1")
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
        // Upload a text string as a new object.
        s3Client.putObject("mollygao", "randomTest.txt", "Uploaded String Object");
    }
}
