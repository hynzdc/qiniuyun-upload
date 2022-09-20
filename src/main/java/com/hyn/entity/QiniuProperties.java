package com.hyn.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "qiniu")
public class QiniuProperties {
    @Value("${qiniu.kodo.accessKey}")
    private String accessKey;
    @Value("${qiniu.kodo.secretKey}")
    private String secretKey;
    @Value("${qiniu.kodo.bucket}")
    private String bucket;
    @Value("${qiniu.kodo.prefix}")
    private String prefix;
}
