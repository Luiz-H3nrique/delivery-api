package com.deliverytech.delivery_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@Tag(name = "Health", description = "Monitoramento e informações do serviço")
public class HealthController {

    @GetMapping("/health")
    @Operation(summary = "Verifica o status da API",
            description = "Retorna informações básicas de saúde do serviço.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Serviço ativo",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Map.class)))
            })
    public Map<String, String> health() {
        return Map.of(
                "status", "UP",
                "timestamp", LocalDateTime.now().toString(),
                "service", "Delivery API",
                "javaVersion", System.getProperty("java.version")
        );
    }

    @GetMapping("/info")
    @Operation(summary = "Informações da aplicação",
            description = "Retorna informações detalhadas sobre a aplicação.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Informações da aplicação",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AppInfo.class)))
            })
    public AppInfo info() {
        return new AppInfo(
                "Delivery Tech API",
                "1.0.0",
                "Luiz Henrique",
                "JDK 21",
                "Spring Boot 3.5.3"
        );
    }

    @GetMapping("/")
    @Operation(summary = "Mensagem de boas-vindas",
            description = "Retorna uma mensagem simples de boas-vindas para confirmar que a API está rodando.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Mensagem de boas-vindas",
                            content = @Content(mediaType = "text/plain"))
            })
    public String home() {
        return "Bem-vindo à Delivery API!";
    }

    public record AppInfo(
            String application,
            String version,
            String developer,
            String javaVersion,
            String framework
    ) {}
}
