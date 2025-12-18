package com.hackaton_one.sentiment_api.api;

import com.hackaton_one.sentiment_api.exceptions.ModelAnalysisException;
import com.hackaton_one.sentiment_api.exceptions.ModelInitializationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Handler global para exceções da API.
 * Centraliza o tratamento de exceções e retorna respostas HTTP apropriadas.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata exceções de inicialização do modelo.
     * Retorna HTTP 500 pois é um erro do servidor na inicialização.
     */
    @ExceptionHandler(ModelInitializationException.class)
    public ResponseEntity<Map<String, Object>> handleModelInitializationException(ModelInitializationException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Model Initialization Error");
        response.put("message", e.getMessage());
        response.put("timestamp", System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    /**
     * Trata exceções durante a análise de sentimento.
     * Retorna HTTP 400 pois é um erro na análise do conteúdo.
     */
    @ExceptionHandler(ModelAnalysisException.class)
    public ResponseEntity<Map<String, Object>> handleModelAnalysisException(ModelAnalysisException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Analysis Error");
        response.put("message", e.getMessage());
        response.put("timestamp", System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Trata exceções genéricas não capturadas.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception e) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Internal Server Error");
        response.put("message", "An unexpected error occurred");
        response.put("timestamp", System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

