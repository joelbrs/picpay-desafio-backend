package br.com.picpay.domain;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String fullName;
    private String email;
    private String cpf;
    private String password;
}
