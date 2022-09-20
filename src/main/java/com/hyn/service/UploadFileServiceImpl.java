package com.hyn.service;

import com.alibaba.fastjson.JSON;
import com.hyn.entity.QiniuProperties;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.UUID;

/**
 * @Auther: hyn
 * @Date: 2022/9/18 - 09 - 11:55
 * @Description: com.hyn.service
 * @Version: 1.0
 */
@Service
@Slf4j
public class UploadFileServiceImpl implements UploadFileService{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static Integer count = 0;
    @Autowired
    private QiniuProperties qiniuProperties;
    @Override
    public String saveFile(MultipartFile uploadFile) {
        Configuration cfg = new Configuration(Region.huanan());
        UploadManager uploadManager = new UploadManager(cfg);
        String bucket = "hynzdc";
        String filname = uploadFile.getOriginalFilename();
        //String key = UUID.randomUUID() + filname.substring(filname.lastIndexOf("."));
        String key = (++count).toString() + filname.substring(filname.lastIndexOf("."));
//        if (!filname.substring(filname.lastIndexOf(".")).equalsIgnoreCase(".pdf")){
//            String key = (++count).toString() + filname.substring(filname.lastIndexOf("."));
//        }

        if (filname.substring(filname.lastIndexOf(".")).equalsIgnoreCase(".pdf")){
            key = System.currentTimeMillis() +  filname.substring(filname.lastIndexOf("."));
        }
        if (filname.substring(filname.lastIndexOf(".")).equalsIgnoreCase(".html")){
            key = System.currentTimeMillis() +  filname.substring(filname.lastIndexOf("."));
        }
        Auth auth = Auth.create(qiniuProperties.getAccessKey(), qiniuProperties.getSecretKey());
        String uploadToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(uploadFile.getBytes(), key, uploadToken);
            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            return "http://ricyi7fln.hn-bkt.clouddn.com/" + putRet.key;
        } catch (QiniuException e) {
            logger.error("上传文件失败", e);
            logger.error(JSON.toJSONString(e.response));
        } catch (IOException e) {
            logger.error("上传文件失败", e);
        }
        return null;
    }
}
