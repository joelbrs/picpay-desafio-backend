package br.com.picpay.application.mapper.database;

import br.com.picpay.domain.enums.TransferStatus;
import org.jdbi.v3.core.argument.Argument;
import org.jdbi.v3.core.argument.ArgumentFactory;
import org.jdbi.v3.core.config.ConfigRegistry;

import java.lang.reflect.Type;
import java.util.Optional;

public class TransferStatusArgumentFactory implements ArgumentFactory {

    @Override
    public Optional<Argument> build(Type type, Object value, ConfigRegistry config) {
        if (value instanceof TransferStatus) {
            return Optional.of((position, statement, ctx) -> {
                statement.setString(position, ((TransferStatus) value).getValue());
            });
        }
        return Optional.empty();
    }
}
