package com.polarbookshop.edgeservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Configuration
public class WebEndpoints {

    // Функциональные конечные точки REST определяются в bean-компоненте
    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route()                                   // Предлагает удобный API для построения маршрутов
                .GET("/catalog-fallback",request ->                          // Резервный ответ, используемый для обработки конечной точки GET
                        ServerResponse.ok().body(Mono.just(""),String.class))
                .POST("/catalog-fallback",request ->                         // Резервный ответ, используемый для обработки конечной точки POST
                        ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).build())
                .build();                                                  // Создает функциональные конечные точки
    }

}
