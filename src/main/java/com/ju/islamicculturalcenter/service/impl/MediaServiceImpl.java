package com.ju.islamicculturalcenter.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ju.islamicculturalcenter.dto.response.MediaResponse;
import com.ju.islamicculturalcenter.exceptions.ValidationException;
import com.ju.islamicculturalcenter.service.iservice.MediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@Slf4j
public class MediaServiceImpl implements MediaService {

    private final AmazonS3 amazonS3;

    @Value("${icc.spaces.name}")
    private String bucketName;

    @Value("${icc.spaces.download}")
    private String bucketDownload;

    public MediaServiceImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public MediaResponse uploadMedia(MultipartFile multipartFile){

        if (multipartFile.isEmpty()) {
            throw new ValidationException("file can't be empty");
        }
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());

        String fileExtension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename().toLowerCase());
        String key = getFileKey(multipartFile, fileExtension); //Unique file key

        PutObjectRequest putObjectRequest = null;
        try {
            putObjectRequest = new PutObjectRequest(bucketName, key, multipartFile.getInputStream(), objectMetadata);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't extract media content.", e);
        }
        putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);

        amazonS3.putObject(putObjectRequest);
        return MediaResponse.builder()
                .mediaUrl(bucketDownload.concat(key))
                .mediaType(fileExtension)
                .mimeType(multipartFile.getContentType())
                .build();


    }

    private String getFileKey(MultipartFile file, String documentType) {
        return file.getOriginalFilename()
                .substring(0, file.getOriginalFilename().indexOf("."))
                .concat(LocalDateTime.now().toString())
                .concat(".".concat(documentType));
    }
}
