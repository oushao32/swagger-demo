package com.oushao.config;

import com.oushao.controller.HelloController;
import io.swagger.models.Path;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.service.ApiInfo.DEFAULT_CONTACT;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }

    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }

    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");
    }



    //配置了swagger的docker的bean实例
    @Bean
    public Docket docket(Environment environment){
//        RequestHandlerSelectors配置要扫描接口的方式
//        basePackage指定扫描的包
//        any():扫描全部
//        none():不扫描
//        withClassAnnotation扫描类上的注解
//        withMethodAnnotation扫描方法上的注解
//        paths()过滤什么路径
        Profiles profiles = Profiles.of("dev","test");
        //获取项目环境
        boolean flag = environment.acceptsProfiles(profiles);
        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)//enable是否启动swagger，如果为False，则swagger不能再被浏览器选中访问
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.oushao.controller"))
                .build();
        //.paths(PathSelectors.ant("/oushao/**"))
    }
    //配置swagger信息=apiInfo
    private ApiInfo apiInfo(){
        return new ApiInfo(
                "啦啦啦的SwaggerApi文档",
                "嘻嘻哈哈哈哈哈哈哈",
                "v1.0",
                "urn:tos", DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
