package com.molly.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.molly.service.StorageService;
import com.molly.service.jms.MessageSQSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;

import static javax.swing.plaf.basic.BasicHTML.propertyKey;

@Configuration
@EnableJpaRepositories(basePackages ="com.molly.repository")
@EnableTransactionManagement

@ComponentScan(basePackages = "com.molly",
    excludeFilters = @ComponentScan.Filter(type= FilterType.REGEX,pattern = "com.molly.api.*"))
public class AppConfig {
    @Autowired
    private Environment environment;
    private final Logger logger = LoggerFactory.getLogger(getClass());

//    @Value("#{databaseProperties['amazon.s3.bucket']}")
//    protected String bucketKey;

    @Bean(name = "databaseProperties")
    public PropertiesFactoryBean getDbProperties(){
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        String profile = environment.getActiveProfiles()[0];
        logger.debug("databaseProperties is "+profile);
        bean.setLocation(new ClassPathResource("META-INF/env/"+profile+"-db.properties"));
        return bean;
    }
    @Bean
    public StorageService getStorageService(@Autowired@Qualifier("databaseProperties")PropertiesFactoryBean beanFactory)throws IOException {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion("us-east-1").withCredentials(new DefaultAWSCredentialsProviderChain()).build();
        StorageService storageService = new StorageService(s3Client);
      storageService.setBucket(beanFactory.getObject().getProperty("amazon.s3.bucket"));
        return storageService;
    }

    @Bean
    public MessageSQSService getMessageService(@Autowired@Qualifier("databaseProperties")PropertiesFactoryBean beanFactory)throws IOException{

        AmazonSQS sqs = AmazonSQSClientBuilder.standard().withRegion("us-east-1").withCredentials(new DefaultAWSCredentialsProviderChain()).build();
        MessageSQSService messageSQSService = new MessageSQSService(sqs,"https://sqs.us-east-1.amazonaws.com/920616494522/foodDemo");
        return messageSQSService;
    }

    @Bean
    public AmazonSQS getAmazonSQS(){
        AmazonSQS client = AmazonSQSClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).build();
        return client;
    }

}
