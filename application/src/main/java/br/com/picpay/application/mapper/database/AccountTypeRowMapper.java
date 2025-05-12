package br.com.picpay.application.mapper.database;

import br.com.picpay.domain.enums.AccountType;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountTypeRowMapper implements RowMapper<AccountType> {
    @Override
    public AccountType map(ResultSet rs, StatementContext ctx) throws SQLException {
        return AccountType.fromValue(rs.getString("type"));
    }
}
