package br.com.jefferson.cryptography.controller.dto;

public record CreateTransactionRequest(String userDocument,
                                        String creditCardToken,
                                        Long value) {

}
