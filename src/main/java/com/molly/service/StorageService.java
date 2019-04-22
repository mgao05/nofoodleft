package com.molly.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.molly.domain.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;

import java.io.File;
import java.io.IOException;

public class StorageService {
    private AmazonS3 s3;
    private String bucket;
    private String objectUrl;
    //@Autowired @Qualifier("databaseProperties") PropertiesFactoryBean beanFactory



    public StorageService(AmazonS3 s3){
        this.s3 = s3;
    };

    public void putObject(String S3key, File file){
        putObject(bucket, S3key, file);}

    public void putObject(String bucket,String S3key, File file){
        s3.putObject(bucket, S3key, file);
    }

    public S3Object getObject(String S3key){
        if(S3key==null){
            return null;
        }else{
            return s3.getObject(bucket, S3key);
        }
    }

    public S3Object getObject(String bucket, String S3key){
        return s3.getObject(bucket, S3key);}

//    public String getObjectUrl(Image image){
//        return image.getObjectUrl();
//    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getObjectUrl() {
        return objectUrl;
    }

    public void setObjectUrl(String objectUrl) {
        this.objectUrl = objectUrl;
    }
}
