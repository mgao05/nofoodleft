package com.molly.service;

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

import java.lang.Object.*;

@Service
public class ImageService {
    @Autowired
    private StorageService storageService;
    @Autowired
    private ImageRepository imageRepository;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static String getExtension(String filename){
        
    }
    @Transactional
    public Image saveFakeImage(MultipartFile multipartFile, boolean isPublic)throws ServiceException {
        if(multipartFile == null || multipartFile.isEmpty())throw new ServiceException("File must be valid");



        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());

    }



}

