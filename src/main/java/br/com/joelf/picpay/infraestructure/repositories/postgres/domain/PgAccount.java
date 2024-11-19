package br.com.joelf.picpay.infraestructure.repositories.postgres.domain;

import br.com.joelf.picpay.domain.entities.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_account")
@Getter
@Setter
@NoArgsConstructor
public class PgAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "fk_user_id")
    private PgUser user;
    private BigDecimal balance;
}
