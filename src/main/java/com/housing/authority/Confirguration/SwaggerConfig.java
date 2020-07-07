package com.housing.authority.Confirguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration

@Primary
public class SwaggerConfig
{

//    @Bean
//    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(paths())
//                .build();
//    }
//    private ApiInfo apiInfo(){
//        return new ApiInfoBuilder()
//                .title("Housing Autority Api")
//                .description("description")
//                .version("1.0-SNAPSHOT")
//                .build();
//    }
//    private Predicate<String> paths(){
//        return Predicates.and(
//                PathSelectors.regex("/.*"),
//                Predicates.not(PathSelectors.regex("/error.*"))
//        );
//    }
}
