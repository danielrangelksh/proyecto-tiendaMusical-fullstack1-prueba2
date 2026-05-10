package com.duoc.pedidos.webClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class ProductoClient {
    private final WebClient webClient;

    public ProductoClient(@Value("${producto-service.url}") String productoServidor){
        this.webClient = WebClient.builder().baseUrl(productoServidor).build();
    }

    public Map<String, Object> obtenerProductoId(Integer id, String token){
        return this.webClient.get()
                .uri("/{id}", id)
                .header("Authorization", token)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                        response -> response.bodyToMono(String.class)
                                .map(body -> new RuntimeException("Producto no encontrado")))
                .bodyToMono(Map.class)
                .block();
    }
}
