//package com.example.AttendenceMyBat;
//
//
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.service.VendorExtension;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//
//import static springfox.documentation.builders.PathSelectors.regex;
//
//import java.util.ArrayList;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    @Bean
//    public Docket atividadeApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.example.AttendenceMyBat"))
//                .paths(regex("/api.*"))
//                .build()
//                .apiInfo(metaInfo());
//    }
//
//    private ApiInfo metaInfo() {
//
//        ApiInfo apiInfo = new ApiInfo(
//                "API REST",
//                "Learing Swagger",
//                "1.0",
//                "Terms of Service",
//                new Contact("Sudhanshu Nigam", "www.github/confusedgenius",
//                        "Sudhanshunigam9839@gmail.com"),
//                "Apache License Version 2.0",
//                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
//        );
//
//        return apiInfo;
//    }
//}
