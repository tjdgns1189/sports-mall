package edu.spring.mall.domain;

public class MemberVO {
	private String memberId;
	private String password;
	private String name;
	private String phone;
	private String email;
	private String address;
	private String userGrade;
	public MemberVO(String memberId, String password, String name, String phone, String email, String adress,
			String userGrade) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = adress;
		this.userGrade = userGrade;
	}
	public MemberVO() {
		super();
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	//test
	public String getAdress() {
		return address;
	}
	public void setAdress(String adress) {
		this.address = adress;
	}
	public String getUserGrade() {
		return userGrade;
	}    
	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}
	@Override
	public String toString() {
		return "MemberVO [memberId=" + memberId + ", password=" + password + ", name=" + name + ", phone=" + phone
				+ ", email=" + email + ", adress=" + address + ", userGrade=" + userGrade + "]";
	}
	
	
	//
}
