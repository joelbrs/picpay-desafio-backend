package br.com.joelf.picpay.infraestructure.repositories.postgres.domain;

import br.com.joelf.picpay.domain.entities.User;
import br.com.joelf.picpay.domain.entities.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
@NoArgsConstructor
public class PgUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String cpfCnpj;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @OneToOne(mappedBy = "user")
    private PgAccount account;
}
