package br.com.picpay.application.infrastructure.database;

import br.com.picpay.application.mapper.database.AccountTypeRowMapper;
import br.com.picpay.domain.enums.AccountType;
import br.com.picpay.ports.AccountRepository;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

public interface JdbiAccountRepository extends AccountRepository {

    @Override
    @UseRowMapper(AccountTypeRowMapper.class)
    @SqlQuery(
        "select a.type from account a where a.id = :id;"
    )
    AccountType findAccountType(@Bind("id") Long id);
}
