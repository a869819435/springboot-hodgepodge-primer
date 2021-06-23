package com.mycompany.myspringboot.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ywq
 * @version v1.0.0
 * @date 2020-06-23 17:37
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    public String name;
    public String version;
    public boolean checkAccessToken;
    public boolean autoToken;

    //下载路径
    public String downloadPath;
    //上传路径
    public String uploadPath;
}
