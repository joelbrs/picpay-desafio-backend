package br.com.joelf.picpay.infraestructure.repositories.postgres;

import br.com.joelf.picpay.infraestructure.repositories.postgres.domain.PgAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<PgAccount, UUID> {

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM PgAccount a WHERE a.user.id = :userId AND a.balance >= :value")
    boolean hasBalance(UUID userId, BigDecimal value);

    @Query("SELECT a FROM PgAccount a WHERE a.user.id = :userId")
    PgAccount findByUser(UUID userId);

    @Modifying
    @Query("UPDATE PgAccount a SET a.balance = a.balance + :value WHERE a.user.id = :userId")
    void updateBalance(UUID userId, BigDecimal value);
}
