package com.ju.islamicculturalcenter.service.iservice;

import com.ju.islamicculturalcenter.dto.response.MediaResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MediaService {

    MediaResponse uploadMedia(MultipartFile multipartFile);
}
