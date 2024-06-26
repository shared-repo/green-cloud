package com.demoweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demoweb.dao.MemberDao;
import com.demoweb.dao.OracleMemberDao;
import com.demoweb.service.AccountService;
import com.demoweb.service.AccountServiceImpl;

@Configuration
public class RootConfiguration {
	
	@Bean
	public MemberDao memberDao() {
		return new OracleMemberDao();
	}
	
	@Bean AccountService accountService() {
		AccountServiceImpl accountService = new AccountServiceImpl();
		accountService.setMemberDao(memberDao());
		return accountService;
	}

}







