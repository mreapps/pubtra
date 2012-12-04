package com.mreapps.repository.jpa.config;

import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableTransactionManagement
public class JpaPersistenceConfiguration
{
    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.user}")
    private String jdbcUser;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Value("${jpa.database}")
    private Db jpaDb;

    @Value("${jpa.showSql}")
    private boolean jpaShowSql;

    @Value("${jpa.generateDdl}")
    private boolean jpaGenereateDdl;

    @Value("${jpa.packages}")
    private String packages;

    @SuppressWarnings("UnusedDeclaration")
    public enum Db
    {
        POSTGRESQL("org.postgresql.Driver", Database.POSTGRESQL),
        MYSQL("com.mysql.jdbc.Driver", Database.MYSQL),
        DERBY("org.apache.derby.jdbc.EmbeddedDriver", Database.DERBY),
        HSQL("org.hsqldb.jdbcDriver", Database.HSQL),
        DB2("com.ibm.as400.access.AS400JDBCDriver", Database.DB2);

        private final String jdbcClass;
        private final Database springDb;

        private Db(String jdbcClass, Database springDb)
        {
            this.jdbcClass = jdbcClass;
            this.springDb = springDb;
        }
    }

    @Bean
    @DependsOn({"dataSource"})
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean()
    {
        final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(this.dataSource());
        factoryBean.setPackagesToScan(packages.replace(" ", "").split(","));
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter()
        {
            {
                setDatabase(jpaDb.springDb);
                setShowSql(jpaShowSql);
                setGenerateDdl(jpaGenereateDdl);
            }
        });
        return factoryBean;
    }

    @Bean
    public DataSource dataSource()
    {
        final BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(jpaDb.jdbcClass);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(jdbcUser);
        dataSource.setPassword(jdbcPassword);

        dataSource.setIdleConnectionTestPeriod(240, TimeUnit.MINUTES);
        dataSource.setIdleMaxAge(60, TimeUnit.MINUTES);
        dataSource.setMaxConnectionsPerPartition(20);
        dataSource.setMinConnectionsPerPartition(2);
        dataSource.setPartitionCount(2);
        dataSource.setAcquireIncrement(2);
        dataSource.setReleaseHelperThreads(3);

        dataSource.setCloseOpenStatements(false);
        dataSource.setDisableConnectionTracking(true);
        return dataSource;
    }

    @Bean
    @DependsOn({"entityManagerFactoryBean"})
    public PlatformTransactionManager transactionManager()
    {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(this.entityManagerFactoryBean().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation()
    {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}