package br.com.picpay.application.config;

import br.com.picpay.application.infrastructure.database.JdbiAccountRepository;
import br.com.picpay.application.infrastructure.database.JdbiTransferRepository;
import br.com.picpay.ports.AccountRepository;
import br.com.picpay.ports.TransferRepository;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class RepositoryConfig {

    @Bean
    public Jdbi jdbi(DataSource ds, List<JdbiPlugin> jdbiPlugins, List<RowMapper<?>> rowMappers) {
        TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(ds);
        Jdbi jdbi = Jdbi.create(proxy);

        jdbiPlugins.forEach(jdbi::installPlugin);
        rowMappers.forEach(jdbi::registerRowMapper);

        return jdbi;
    }

    @Bean
    public JdbiPlugin jdbiPlugin() {
        return new SqlObjectPlugin();
    }

    @Bean
    public AccountRepository accountRepository(Jdbi jdbi) {
        return jdbi.onDemand(JdbiAccountRepository.class);
    }

    @Bean
    public TransferRepository transferRepository(Jdbi jdbi) {
        return jdbi.onDemand(JdbiTransferRepository.class);
    }
}
