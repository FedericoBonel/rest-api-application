package com.federicobonel.restapiapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public final static String CATEGORY_TAG = "Category Service API";
    public final static String CATEGORY_DESC = "Service that handles all shop categories";

    public final static String CUSTOMER_TAG = "Customer Service API";
    public final static String CUSTOMER_DESC = "Service that handles all shop customers";

    public final static String VENDOR_TAG = "Vendor Service API";
    public final static String VENDOR_DESC = "Service that handles all shop vendors";

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(getMetaData())
                .tags(new Tag(CATEGORY_TAG, CATEGORY_DESC),
                        new Tag(CUSTOMER_TAG, CUSTOMER_DESC),
                        new Tag(VENDOR_TAG, VENDOR_DESC));
    }

    private ApiInfo getMetaData() {

        Contact contact = new Contact("Federico Jorge Bonel Tozzi",
                "https://github.com/FedericoBonel",
                "Bonelfederico@gmail.com");

        return new ApiInfo(
                "Shop RestAPI",
                "This is a sample Shop RestAPI that exposes inner information to other applications through services.",
                "1.0.0",
                "https://smartbear.com/terms-of-use/",
                contact,
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
                );
    }
}
