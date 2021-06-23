package com.mycompany.myspringboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;

@Configuration
@EnableSwagger2
@ConditionalOnBean(Swagger2Config.class)
public class Swagger2Config {

    @Autowired
    private AppConfig appConfig;

    /**
     * 全局设置Content Type，默认是application/json
     * 如果想只针对某个方法，则注释掉改语句，在特定的方法加上下面信息
     * @ApiOperation(consumes="application/x-www-form-urlencoded")
     */
    public static final HashSet<String> consumes = new HashSet<String>() {{
        add("application/x-www-form-urlencoded");
    }};

    @Bean(value = "commonApi")
    public Docket commonApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("通用公共类接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mycompany.myspringboot.controller.common"))
                .paths(PathSelectors.any())
                .build()
                //.securityContexts(Lists.newArrayList(securityContext())).securitySchemes(Lists.<SecurityScheme> newArrayList(apiKey()))
                .consumes(consumes);
    }

    @Bean(value = "systemApi")
    public Docket systemApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("系统权限管理接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mycompany.myspringboot.controller.system"))
                .paths(PathSelectors.any())
                .build()
                //.securityContexts(Lists.newArrayList(securityContext())).securitySchemes(Lists.<SecurityScheme> newArrayList(apiKey()))
                .consumes(consumes);
    }

    @Bean(value = "ecpBaseDataApi")
    public Docket ecpBaseDataApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("基础数据管理")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mycompany.myspringboot.controller.config"))//扫描包
                .paths(PathSelectors.any())
                .build()
                //.securityContexts(Lists.newArrayList(securityContext())).securitySchemes(Lists.<SecurityScheme> newArrayList(apiKey()))
                .consumes(consumes);
    }

    @Bean(value = "businessBaseDataApi")
    public Docket businessBaseDataApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("用户信息模块")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mycompany.myspringboot.user.controller"))//扫描包
                .paths(PathSelectors.any())
                .build()
                //.securityContexts(Lists.newArrayList(securityContext())).securitySchemes(Lists.<SecurityScheme> newArrayList(apiKey()))
                .consumes(consumes);
    }
    @Bean(value = "scheduleApi")
    public Docket scheduleApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("外部调用定时同步测试接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mycompany.myspringboot.controller.synch"))
                .paths(PathSelectors.any())
                .build()
                //.securityContexts(Lists.newArrayList(securityContext())).securitySchemes(Lists.<SecurityScheme> newArrayList(apiKey()))
                .consumes(consumes);
    }

    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo() {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                //标题
                .title("swagger2Demo文档")
                //简介
                .description("")
                //服务条款
                .termsOfServiceUrl("")
                //作者个人信息
                .contact(new Contact(appConfig.name, null, null))
                .version("版本号:" + appConfig.version)
                .build();
    }
}
