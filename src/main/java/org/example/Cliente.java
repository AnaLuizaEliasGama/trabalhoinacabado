package org.example;

public class Cliente {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    // Construtor, getters e setters

    public void setCpf(String cpf) {
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido. Deve conter 11 dígitos numéricos.");
        }
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Email inválido. Deve conter '@' e domínio.");
        }
        this.email = email;
    }
}