package br.com.picpay.application.infrastructure.database;

import br.com.picpay.application.mapper.database.TransferStatusArgumentFactory;
import br.com.picpay.domain.Transfer;
import br.com.picpay.ports.TransferRepository;
import org.jdbi.v3.sqlobject.config.RegisterArgumentFactory;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.math.BigDecimal;

public interface JdbiTransferRepository extends TransferRepository {

    @Override
    @SqlQuery(
        "select\n" +
            "  (\n" +
            "    coalesce(sum(case when t.payee_account_id = a.id then t.amount else 0 end), 0) -\n" +
            "    coalesce(sum(case when t.payer_account_id = a.id then t.amount else 0 end), 0)\n" +
            "  ) >= :amount as available_balance\n" +
        "from\n" +
            "  account a\n" +
        "left join\n" +
            "  transfer t on t.payee_account_id = a.id or t.payer_account_id = a.id\n" +
        "where\n" +
        "  a.id = :accountId\n" +
        "  and t.status = 'PROCESSADA_SUCESSO' or status = 'EM_PROCESSAMENTO';"
    )
    boolean isSufficientBalance(Long accountId, BigDecimal amount);

    @Override
    @RegisterArgumentFactory(TransferStatusArgumentFactory.class)
    @SqlUpdate(
        "insert into transfer (\n" +
            "  payer_account_id,\n" +
            "  payee_account_id,\n" +
            "  amount,\n" +
            "  idempotency_id,\n" +
            "  status\n" +
        ") values (\n" +
            "  :payer,\n" +
            "  :payee,\n" +
            "  :amount,\n" +
            "  :idempotencyId,\n" +
            "  :status\n" +");")
    @GetGeneratedKeys
    Long create(@BindBean Transfer transfer);

    @Override
    @RegisterArgumentFactory(TransferStatusArgumentFactory.class)
    @SqlUpdate(
        "update transfer set status = :status where id = :id;"
    )
    void update(@Bind("id") Long id, @BindBean Transfer transfer);
}
