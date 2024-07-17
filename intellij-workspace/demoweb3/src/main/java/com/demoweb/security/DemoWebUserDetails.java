package com.demoweb.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.demoweb.dto.MemberDto;
import com.demoweb.dto.RoleDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class DemoWebUserDetails implements UserDetails {

	private MemberDto member;
	private List<RoleDto> roles;
	
	public DemoWebUserDetails() {}
	public DemoWebUserDetails(MemberDto member) {
		this.member = member;
	}
	public DemoWebUserDetails(MemberDto member, List<RoleDto> roles) {
		this.member = member;
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		ArrayList<SimpleGrantedAuthority> grants = new ArrayList<>();
		for (RoleDto role : roles) {
			grants.add(new SimpleGrantedAuthority(role.getRoleName()));			
		}
		return grants;
	}

	@Override
	public String getPassword() {
		return member.getPasswd();
	}

	@Override
	public String getUsername() {
		return member.getMemberId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {		
		return member.isActive();
	}

}
