package com.tje.model;

public class Member {
	private String member_id;
	private String password;
	private String name;
	private String nickname;
	private String tel;
	private String address_post;
	private String address_basic;
	private String address_detail;
	private String interest;
	private int auth;
	private Double latitude;
	private Double longitude;
	private int member_type;
	
	public Member() {
	}

	public Member(String member_id, String password, String name, String nickname, String tel, String address_post,
			String address_basic, String address_detail, String interest, int auth, Double latitude, Double longitude,
			int member_type) {
		this.member_id = member_id;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
		this.tel = tel;
		this.address_post = address_post;
		this.address_basic = address_basic;
		this.address_detail = address_detail;
		this.interest = interest;
		this.auth = auth;
		this.latitude = latitude;
		this.longitude = longitude;
		this.member_type = member_type;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress_post() {
		return address_post;
	}

	public void setAddress_post(String address_post) {
		this.address_post = address_post;
	}

	public String getAddress_basic() {
		return address_basic;
	}

	public void setAddress_basic(String address_basic) {
		this.address_basic = address_basic;
	}

	public String getAddress_detail() {
		return address_detail;
	}

	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public int getMember_type() {
		return member_type;
	}

	public void setMember_type(int member_type) {
		this.member_type = member_type;
	}
	
}
