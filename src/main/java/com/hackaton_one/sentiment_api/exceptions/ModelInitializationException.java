package com.hackaton_one.sentiment_api.exceptions;

/**
 * Exceção lançada quando ocorre um erro ao inicializar o modelo ONNX.
 * Diferente de ModelLoadException, esta é mais específica para erros de inicialização.
 */
public class ModelInitializationException extends RuntimeException {
    public ModelInitializationException(String message) {
        super(message);
    }

    public ModelInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}

