package com.ducdmd152.springboot.dsnackerstore.users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	@Id
	@Column(name="username")
	@NotNull(message="Username is required.")
	@Size(min = 6, max = 20, message="Username is required input from 6 to 20 characters")
	private String username;
	
	@Column(name="password")
	@NotNull(message="Password is required.")
	@Size(min = 8, max = 20, message="Password is required input from 8 to 20 characters")
	private String password;
	
	@Column(name="enabled")
	private boolean enabled = true;
	
	@OneToOne(mappedBy = "user", cascade=CascadeType.ALL)
	private UserDetail userDetail;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "username")
	private List<Authority> authorities;
	public void addAuthority(Authority authority) {
		if(authorities == null) {
			authorities = new ArrayList<>();
		}
		
		authorities.add(authority);
		authority.setUsername(username);
	}
	public List<Authority> getAuthorities() {
		return authorities;
	}


	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
	 
	public User() {
	}

	
	public User(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	
	public UserDetail getUserDetail() {
		return userDetail;
	}


	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", enabled=" + enabled + ", userDetail="
				+ userDetail + ", authorities=" + authorities + "]";
	}

}
