package br.com.joelf.picpay.infraestructure.repositories.postgres.domain;

import br.com.joelf.picpay.domain.entities.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
@Getter
@NoArgsConstructor
public class PgAccount extends Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "fk_user_id")
    private PgUser user;
    private BigDecimal balance = BigDecimal.ZERO;
}
