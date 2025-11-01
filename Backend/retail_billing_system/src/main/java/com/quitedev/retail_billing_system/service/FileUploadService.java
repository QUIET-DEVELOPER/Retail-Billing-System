package com.quitedev.retail_billing_system.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    
    String uploadFile(MultipartFile file);
    boolean deleteFile(String imgUrl);
}
