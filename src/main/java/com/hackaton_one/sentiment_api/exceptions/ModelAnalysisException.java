package com.hackaton_one.sentiment_api.exceptions;

/**
 * Exceção lançada quando ocorre um erro durante a análise de sentimento.
 * Diferente de ModelLoadException, esta é específica para erros durante a execução da inferência.
 */
public class ModelAnalysisException extends RuntimeException {
    public ModelAnalysisException(String message) {
        super(message);
    }

    public ModelAnalysisException(String message, Throwable cause) {
        super(message, cause);
    }
}


