package com.demoweb.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.demoweb.dao.MemberDao;
import com.demoweb.dao.OracleMemberDao;
import com.demoweb.dao.OracleMemberDaoWithConnectionPool;
import com.demoweb.dao.OracleMemberDaoWithJdbcTemplate;
import com.demoweb.dao.OracleMemberDaoWithMyBatis;
import com.demoweb.dao.OracleMemberDaoWithNamedParameterJdbcTemplate;
import com.demoweb.service.AccountService;
import com.demoweb.service.AccountServiceImpl;

@Configuration
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
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource());
	}
	
	@Bean
	public MemberDao memberDao() {
		return new OracleMemberDao();
	}
	
	@Bean
	public MemberDao memberDaoWithConnectionPool() {
		OracleMemberDaoWithConnectionPool memberDao = new OracleMemberDaoWithConnectionPool();
		memberDao.setDataSource(dataSource());
		return memberDao;
	}
	@Bean
	public MemberDao memberDaoWithJdbcTemplate() {
		OracleMemberDaoWithJdbcTemplate memberDao = new OracleMemberDaoWithJdbcTemplate();
		memberDao.setJdbcTemplate(jdbcTemplate());
		return memberDao;
	}
	@Bean
	public MemberDao memberDaoWithNamedParameterJdbcTemplate() {
		OracleMemberDaoWithNamedParameterJdbcTemplate memberDao = new OracleMemberDaoWithNamedParameterJdbcTemplate();
		memberDao.setJdbcTemplate(namedParameterJdbcTemplate());
		return memberDao;
	}
	
	@Bean SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		return factoryBean.getObject();
	}
	
	@Bean SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	@Bean
	public MemberDao memberDaoWithMyBatis() throws Exception {
		OracleMemberDaoWithMyBatis memberDao = new OracleMemberDaoWithMyBatis();
		memberDao.setSqlSessionTemplate(sqlSessionTemplate());
		return memberDao;
	}
	
	@Bean AccountService accountService() throws Exception {
		AccountServiceImpl accountService = new AccountServiceImpl();
		// accountService.setMemberDao(memberDao());
		// accountService.setMemberDao(memberDaoWithConnectionPool());
		// accountService.setMemberDao(memberDaoWithJdbcTemplate());
		// accountService.setMemberDao(memberDaoWithNamedParameterJdbcTemplate());
		accountService.setMemberDao(memberDaoWithMyBatis());
		return accountService;
	}
	
}







