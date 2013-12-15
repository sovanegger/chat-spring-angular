package cz.prf.uai.tomsovsky.chat.configuration;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

import cz.prf.uai.tomsovsky.chat.service.BootstrapService;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages={"cz.prf.uai.tomsovsky.chat.service", "cz.prf.uai.tomsovsky.chat.dao"})
public class ChatPersistenceConfig {
	@Autowired
	private BootstrapService bootstrapService;
	
	@Bean
	public AbstractPlatformTransactionManager transactionManager() {
		SessionFactory sessionFactory = sessionFactory();
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(sessionFactory);
		return hibernateTransactionManager;
	}
	
	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder sessionFactoryBuilder = new LocalSessionFactoryBuilder(dataSource());
		sessionFactoryBuilder.scanPackages(new String[] {"cz.prf.uai.tomsovsky.chat.domain"});
		sessionFactoryBuilder.addProperties(hibernateProperties());
		return sessionFactoryBuilder.buildSessionFactory();
	}
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/chat?characterEncoding=utf8");
		dataSource.setUsername("postgres");
		dataSource.setPassword("Lulut5187");
		return dataSource;
	}
	
	@Bean
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("javax.persistence.validation.mode", "none");
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("hibernate.temp.use_jdbc_metadata_defaults", false);
		properties.put("hibernate.connection.useUnicode", true);
		properties.put("hibernate.connection.characterEncoding", "utf8");
		properties.put("hibernate.connection.charSet", "utf8");
		return properties;
	}
	
	@PostConstruct
	public void initDatabase() {
		bootstrapService.initDatabaseByDummyDates();
	}
	
	public BootstrapService getBootstrapService() {
		return bootstrapService;
	}
	public void setBootstrapService(BootstrapService bootstrapService) {
		this.bootstrapService = bootstrapService;
	}
}
