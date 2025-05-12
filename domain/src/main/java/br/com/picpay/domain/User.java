package br.com.picpay.domain;

import br.com.picpay.domain.enums.UserType;

public class User {
    private Long id;
    private String fullName;
    private String email;
    private String cpf;
    private String password;
    private UserType userType;
}
