package dev.fringe.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:app.properties")
@EnableTransactionManagement
@EnableJpaRepositories("dev.fringe.repositories")
@EntityScan("dev.fringe.entity")
public class HibernateAndMybatisConfig {
	
	public class DataSourceConfig{
		@Value("${app.jdbc.driver}") String driver;
		@Value("${app.jdbc.url}") String url;	
		@Value("${app.jdbc.user}") String user;	
		@Value("${app.jdbc.password}") String password;	
		
		@Bean
		public DataSource dataSource() {
			BasicDataSource ds = new BasicDataSource();
			ds.setDriverClassName(driver);
			ds.setUrl(url);
			ds.setUsername(user);
			ds.setPassword(password);
			return ds;
		}
	}
	
	@Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		return sqlSessionFactoryBean.getObject();
	}
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("dev.fringe.repositories");
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return configurer;
    }	
}
