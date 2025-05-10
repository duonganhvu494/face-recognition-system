package com.vm.face_recognition.controller;

import com.vm.face_recognition.dto.response.ApiResponse;
import com.vm.face_recognition.model.Result;
import com.vm.face_recognition.service.FaceRecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/face")
public class FaceRecognitionController {
    @Autowired
    private FaceRecognitionService faceRecognitionService;

    @PostMapping("/recognize")
    public ApiResponse<Result> recognizeFace(@RequestParam("file") MultipartFile file){
        Result result = faceRecognitionService.recognizeFace(file);
        return ApiResponse.<Result>builder()
                .EC(0)
                .EM("Success")
                .result(result)
                .build();
    }
}
