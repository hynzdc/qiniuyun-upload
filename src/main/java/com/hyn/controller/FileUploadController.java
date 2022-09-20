package com.hyn.controller;

import com.hyn.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: hyn
 * @Date: 2022/9/18 - 09 - 12:06
 * @Description: com.hyn.controller
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class FileUploadController {
    @Autowired
    private UploadFileService uploadFileService;
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest req) {
        if (!uploadFile.isEmpty()) {
            return uploadFileService.saveFile(uploadFile);
        }
        return "上传失败!";
    }
}
