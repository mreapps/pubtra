package com.mreapps.repository.jpa.dao.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ContextConfiguration("classpath:jpa-repository-test-context.xml")
@TransactionConfiguration(defaultRollback = true)
public abstract class AbstractJpaRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests
{
}
