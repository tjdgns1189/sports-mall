package edu.spring.mall.security;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
	private final Logger logger = LoggerFactory.getLogger(CustomUserDetails.class);

	private String memberId;
	private String password;
	private String name;
	private String phone;
	private String email;
	private String address;
	private String userGrade;
	private String isOauthLogin;
	private int zonecode;
	private String detailAddress;

	public String getIsOauthLogin() {
		return isOauthLogin;
	}

	public void setIsOauthLogin(String isOauthLogin) {
		this.isOauthLogin = isOauthLogin;
	}

	public CustomUserDetails(String memberId, String password, String name, String phone, String email, String address,
			String userGrade, String isOauthLogin, int zonecode, String detailAddress) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.userGrade = userGrade;
		this.isOauthLogin = isOauthLogin;
		this.zonecode = zonecode;
		this.detailAddress = detailAddress;
	}

	public int getZonecode() {
		return zonecode;
	}

	public void setZonecode(int zonecode) {
		this.zonecode = zonecode;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CustomUserDetails() {
		super();
	}

	@Override
	public String toString() {
		return "CustomUserDetails [logger=" + logger + ", memberId=" + memberId + ", password=" + password + ", name="
				+ name + ", phone=" + phone + ", email=" + email + ", address=" + address + ", userGrade=" + userGrade
				+ ", isOauthLogin=" + isOauthLogin + "]";
	}

	public CustomUserDetails(String memberId, String password, String name, String phone, String email, String address,
			String userGrade, String isOauthLogin) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.userGrade = userGrade;
		this.isOauthLogin = isOauthLogin;
	}

	public CustomUserDetails(String memberId, String password, String name, String phone, String email, String address,
			String userGrade) {
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.userGrade = userGrade;
	}

	@Override
	// 유저 권한 설정부분
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> authorities = new ArrayList<>();

		if ("ROLE_ADMIN".equals(userGrade)) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}

		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.memberId;
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
		return true;
	}

}
