package com.demoweb.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import com.demoweb.mapper.BoardMapper;
import com.demoweb.mapper.MemberMapper;
import com.demoweb.service.AccountService;
import com.demoweb.service.AccountServiceImpl;
import com.demoweb.service.BoardService;
import com.demoweb.service.BoardServiceImpl;

@Configuration
@MapperScan(basePackages = { "com.demoweb.mapper" }) // root-context.xml의 <mybatis:scan의 역할과 동일
@EnableTransactionManagement // <tx:annotation-driven과 같은 역할
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
	
	@Bean BoardService boardService(BoardMapper boardMapper) throws Exception {
		BoardServiceImpl boardService = new BoardServiceImpl();		
		boardService.setBoardMapper(boardMapper);
		boardService.setTransactionTemplate(transactionTemplate());
		return boardService;
	}
	
	@Bean PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource());
		return transactionManager;
	}
	@Bean TransactionTemplate transactionTemplate() {
		TransactionTemplate transactionTemplate = new TransactionTemplate();
		transactionTemplate.setTransactionManager(transactionManager());
		return transactionTemplate;
	}
	

	
}







