//package com.example.config;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
////import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
////@Enableswagger2
//public class swaggerConfig {
//
//    /**
//     * 添加摘要信息
//     */
//    @Bean
//    public OpenAPI openAPI() {
//
////        return new OpenAPI()
////                .info(new Info().title("hancher demo open api")
////                        .description("helloWorld接口文档")
////                        .version("v3.0.0")
////                        // 联系人
////                        .contact(new Contact().name("寒澈").url("https://www.hancher.top/"))
////                )
////                // 其他文档信息
////                .externalDocs(new ExternalDocumentation()
////                        .description("寒澈笔记")
////                        .url("https://www.hancher.top/"));
//    }
//
////    @Bean
////    public Docket api(){
////        return new Docket(DocumentationType.SWAGGER_2)
////                .select()
////                .apis(RequestHandlerSelectors.any())
////                .paths(PathSelectors.any())
////                .build();
////    }
////
////    public ApiInfo apiInfo() {
////        return new ApiInfoBuilder().title("我的接口文档")
////                .contact(new Contact("Pj","","476589450@qq.com"))
////                .description("这是我的swaggerUI生成的接口文档")
////                .version("1.0.0.0")
////                .build();
////    }
//}
