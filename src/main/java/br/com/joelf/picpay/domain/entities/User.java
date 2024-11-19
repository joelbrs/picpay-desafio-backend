package br.com.joelf.picpay.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class User implements UserDetails {
    private UUID id;
    private String name;
    private String email;
    private String cpfCnpj;
    private String password;
    private UserType type;
    private Account account;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(type);
    }

    @Override
    public String getUsername() {
        return cpfCnpj;
    }
}
