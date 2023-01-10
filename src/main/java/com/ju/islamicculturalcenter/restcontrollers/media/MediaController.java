package com.ju.islamicculturalcenter.restcontrollers.media;

import com.ju.islamicculturalcenter.dto.response.CODE;
import com.ju.islamicculturalcenter.dto.response.MediaResponse;
import com.ju.islamicculturalcenter.dto.response.Response;
import com.ju.islamicculturalcenter.service.iservice.MediaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/v1/user/media")
public class MediaController {

    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping
    public ResponseEntity<Response<MediaResponse>> uploadMedia(@RequestParam MultipartFile file){
        return new ResponseEntity<>(Response.<MediaResponse>builder()
                .data(mediaService.uploadMedia(file))
                .success(true)
                .code(CODE.OK.getId())
                .message(CODE.OK.name()).build(), HttpStatus.OK);
    }
}
