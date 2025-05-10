package com.vm.face_recognition.service;

import com.vm.face_recognition.exception.AppException;
import com.vm.face_recognition.exception.ErrorCode;
import com.vm.face_recognition.model.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Service
public class FaceRecognitionService {
    public Result recognizeFace(MultipartFile file) {
        if (file.isEmpty()) {
            throw new AppException(ErrorCode.FILE_EMPTY);
        }

        try {
            byte[] imageBytes = file.getBytes();
            String base64 = Base64.getEncoder().encodeToString(imageBytes);

            Result result = new Result();
            result.setFilename(file.getOriginalFilename());
            result.setBase64Image(base64);
            result.setMessage("Ảnh đã nhận thành công");

            return result;
        } catch (Exception e) {
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

}
