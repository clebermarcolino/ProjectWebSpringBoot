package com.clebermarcolino.project.entities.enums;

public enum OrderStatus {
    WAITING_PAYMENT(1), // Define a constante WAITING_PAYMENT com o código associado 1.
    PAID(2), // Define a constante PAID com o código associado 2.
    SHIPPED(3), // Define a constante SHIPPED com o código associado 3.
    DELIVERED(4), // Define a constante DELIVERED com o código associado 4.
    CANCELED(5); // Define a constante CANCELED com o código associado 5.

    private int code; // Declaração de uma variável de instância privada chamada 'code' do tipo inteiro. Cada constante do enum terá seu próprio valor para este código.

    private OrderStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static OrderStatus valueOf(int code) { // Método estático público chamado 'valueOf' que recebe um código inteiro como argumento e tenta encontrar a constante OrderStatus correspondente a esse código.
        for(OrderStatus value : OrderStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Esse código é inválido! ");
    }
}
