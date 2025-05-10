package com.vm.face_recognition.model;

import lombok.Data;

import java.util.List;

@Data
public class Result {
//    private String userId;
//    private List<Double> embedding;
//    private String message;

    private String filename;
    private String base64Image;
    private String message;
}
