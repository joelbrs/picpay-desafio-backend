package br.com.joelf.picpay.infraestructure.repositories.postgres;

import br.com.joelf.picpay.infraestructure.repositories.postgres.domain.PgUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<PgUser, UUID> {

    @Query("SELECT u FROM PgUser u WHERE u.cpfCnpj = :cpfCnpj")
    Optional<PgUser> findByCpfCnpj(String cpfCnpj);
}
