package com.molly.service;

import com.amazonaws.services.s3.model.S3Object;
import com.molly.domain.FilenameUtils;
import com.molly.domain.Image;
import com.molly.repository.ImageRepository;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.Object.*;

@Service
public class ImageService {
    @Autowired
    private StorageService storageService;
    @Autowired
    private ImageRepository imageRepository;
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Transactional

    public Image saveFakeImage(MultipartFile multipartFile, boolean isPublic)throws ServiceException{
        if(multipartFile == null || multipartFile.isEmpty())throw new ServiceException("File must be valid");

        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());

        // String homeDir = System.getProperty("cataline.base") !=null ? System.getProperty("cataline.base");
        Image image = new Image();
        String s3Key = FilenameUtils.getBaseName(multipartFile.getOriginalFilename())+ "_"+image.getUuid()+image.getExtension();
        File localFile = new File(homeDir + s3Key);

        try{
            multipartFile.transferTo(localFile);
            storageService.putObject(s3Key,localFile);
            S3Object s3Object = storageService.getObject(s3Key);
            image.setUrl(storageService.getObjectUrl(s3Object.getKey()));
            image.setExtension(extension);
            return image;
        }catch (IOException e){
            logger.warn("can't find image file");
        }
        return null;


    }



}

