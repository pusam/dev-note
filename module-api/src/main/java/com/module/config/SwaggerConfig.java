package com.module.config;

import com.fasterxml.classmate.TypeResolver;
import com.module.util.MessageUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import com.module.response.RestResponse.RestResultResponse;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@Configuration
public class SwaggerConfig {

    @Value("${swagger.group-name:Version 1.0}")
    private String groupName;

    @Value("${swagger.title:Swagger Title}")
    private String title;

    @Value("${swagger.ver:0}")
    private String ver;

    @Value("${swagger.base-package:com.module.api}")
    private String basePackage;

    @Bean
    public Docket api(TypeResolver typeResolver) {

        return new Docket(DocumentationType.OAS_30) // 3.0 문서버전으로 세팅
                .useDefaultResponseMessages(true)
                .groupName(groupName)
                .apiInfo(apiInfo())
                .globalResponses(HttpMethod.GET, errorResponseList())
                .globalResponses(HttpMethod.POST, errorResponseList())
                .globalResponses(HttpMethod.PUT, errorResponseList())
                .globalResponses(HttpMethod.DELETE, errorResponseList())
                .globalResponses(HttpMethod.PATCH, errorResponseList())
                .additionalModels(typeResolver.resolve(RestResultResponse.class))
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private List<Response> errorResponseList() {

        List<Response> list = new ArrayList<>();
        list.add(errorResponse("400", HttpStatus.BAD_REQUEST.getReasonPhrase()));
        list.add(errorResponse("404", HttpStatus.NOT_FOUND.getReasonPhrase()));
        list.add(errorResponse("500", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));

        return list;
    }

    private Response errorResponse(String code, String description) {
        return new ResponseBuilder()
                .code(code)
                .description(description)
                .representation(MediaType.APPLICATION_JSON)
                .apply(response -> response.model(model ->
                        model.referenceModel(referenceModel ->
                                referenceModel.key(key ->
                                        key.qualifiedModelName(qualifiedModelName ->
                                                qualifiedModelName.namespace(RestResultResponse.class.getPackage().getName())
                                                        .name(RestResultResponse.class.getSimpleName())
                                        )
                                )
                        )
                ))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description())
                .version(ver)
                .build();
    }

    private String description() {

        StringBuilder sb = new StringBuilder();
        sb.append("## Custom Response Code\n");
        sb.append("| Status Code | Reason  | Status Code | Reason  |\n");
        sb.append("| ----------- | --------| ----------- | --------|\n");
        sb.append("| 200 | ").append(MessageUtils.getMessage("message.success")).append(" ");
        sb.append("| 1000 | ").append(MessageUtils.getMessage("exception.message.1000")).append(" |\n");


        for(int i=2000; i<=2100; i++) {
            if(!MessageUtils.getMessage("exception.message." + i).startsWith("exception.message")) {
                if(i % 2 == 0) {
                    sb.append("| ").append(i).append(" | ").append(MessageUtils.getMessage("exception.message." + i)).append(" ");
                } else {
                    sb.append("| ").append(i).append(" | ").append(MessageUtils.getMessage("exception.message." + i)).append(" |\n");
                }
            }
        }
        return sb.toString();
    }

    @Bean
    public UiConfiguration uiConfiguration() {
        final String[] supportedSubmitMethods = {"get", "put", "post", "delete", "options", "head", "patch", "trace"};
        return UiConfigurationBuilder.builder()
                .docExpansion(DocExpansion.LIST)
                .supportedSubmitMethods(supportedSubmitMethods)
                .displayOperationId(false)
                .defaultModelExpandDepth(5)
                .defaultModelsExpandDepth(-1)
                .build();
    }
}