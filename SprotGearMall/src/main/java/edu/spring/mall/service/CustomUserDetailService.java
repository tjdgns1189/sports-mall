package edu.spring.mall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.spring.mall.persistence.MemberDAO;
import edu.spring.mall.security.CustomUserDetails;

public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private MemberDAO dao;

	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
			CustomUserDetails user = dao.login(username);
			if(user == null) {
		    throw new UsernameNotFoundException("아이디를 찾을 수 없음 " + username);
		    }
		    return user;
	}
	

}
