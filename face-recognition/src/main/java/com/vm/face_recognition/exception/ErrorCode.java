package com.vm.face_recognition.exception;

public enum ErrorCode {
    INTERNAL_SERVER_ERROR(5, "Lỗi hệ thống"),
    FILE_EMPTY(1, "File is empty"),
    INVALID_FILE_FORMAT(1, "Invalid file format"),
    FACE_NOT_DETECTED(1, "No face detected in image");

    private final int EC;
    private final String EM;

    ErrorCode(int EC, String EM) {
        this.EC = EC;
        this.EM = EM;
    }

    public int getEC() {
        return EC;
    }

    public String getEM() {
        return EM;
    }
}
