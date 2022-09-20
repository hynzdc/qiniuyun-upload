package com.hyn.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Auther: hyn
 * @Date: 2022/9/18 - 09 - 11:55
 * @Description: com.hyn.service
 * @Version: 1.0
 */
public interface UploadFileService {
    public String saveFile(MultipartFile uploadFile);
}
