package com.yyjccc.studentsystem.Configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "auth")
public class AuthrizationConfig {
    //不需要权限校验的权限列表
    private List<String> ignoreUrls;
}
