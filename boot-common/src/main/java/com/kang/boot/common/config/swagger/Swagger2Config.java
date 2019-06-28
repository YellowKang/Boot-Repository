package com.kang.boot.common.config.swagger;

import com.google.common.base.Predicates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Slf4j
public class Swagger2Config {
    //获取swagger配置title
    @Value("${swagger.title:请设置配置}")
    private String title;
    //获取swagger配置description
    @Value("${swagger.description:请设置配置}")
    private String description;
    //获取swagger配置version
    @Value("${swagger.version:请设置配置}")
    private String version;

    @Bean
    public Docket webApiConfig() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(webApiInfo())// 调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
                    .select()//创建ApiSelectorBuilder对象
                    .apis(RequestHandlerSelectors.basePackage("com.kang.boot"))//扫描的包
                    .paths(Predicates.not(PathSelectors.regex("/error.*")))//过滤掉错误路径
                    .build();
    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(version)
                .build();
    }

}
