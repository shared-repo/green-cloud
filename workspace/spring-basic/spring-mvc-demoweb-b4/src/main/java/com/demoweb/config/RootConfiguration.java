package com.demoweb.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.demoweb.mapper.MemberMapper;
import com.demoweb.service.AccountService;
import com.demoweb.service.AccountServiceImpl;

@Configuration
@MapperScan(basePackages = { "com.demoweb.mapper" })
public class RootConfiguration {
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521/xe");
		dataSource.setUsername("green_cloud");
		dataSource.setPassword("oracle");
		
		dataSource.setInitialSize(10);
		dataSource.setMaxTotal(20);
		dataSource.setMaxIdle(10);
		
		return dataSource;
	}
	
	@Bean SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		return factoryBean.getObject();
	}
	
	@Bean AccountService accountService(MemberMapper memberMapper) throws Exception {
		AccountServiceImpl accountService = new AccountServiceImpl();		
		accountService.setMemberMapper(memberMapper);
		return accountService;
	}
	
}







