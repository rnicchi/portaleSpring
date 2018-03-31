package jrn.dao.entities;

import java.util.List;
 

public class User {
	private int login_id;
	private String username;
	private String password;
	private String email;
	private String tel;
	private int enabled;
	
	private List<Roles> userRoles;

	
	public User(){}
	
	
	public User(int login_id, String username, String password, String email,
			String tel, int enabled) {
		super();
		this.login_id = login_id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.tel = tel;
		this.enabled = enabled;
		
	}
	
	
	public User(String username, String password, String email,
			String tel, int enabled) {
		super();
		
		this.username = username;
		this.password = password;
		this.email = email;
		this.tel = tel;
		this.enabled = enabled;
		
	}
	
	public User(int login_id, String password, String email,
			String tel, int enabled) {
		super();
		
		this.login_id = login_id;
		this.password = password;
		this.email = email;
		this.tel = tel;
		this.enabled = enabled;
		
	}
	


	public int getLogin_id() {
		return login_id;
	}
	public void setLogin_id(int login_id) {
		this.login_id = login_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public List<Roles> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(List<Roles> userRoles) {
		this.userRoles = userRoles;
	}
	
	
	
	
}
