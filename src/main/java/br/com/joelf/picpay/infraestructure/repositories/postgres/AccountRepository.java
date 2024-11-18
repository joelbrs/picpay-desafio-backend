package br.com.joelf.picpay.infraestructure.repositories.postgres;

import br.com.joelf.picpay.infraestructure.repositories.postgres.domain.PgAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<PgAccount, UUID> {
}
