package com.ducdmd152.springboot.dsnackerstore.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="authorities")
@IdClass(AuthorityId.class)
public class Authority {
	@Id
	@Column(name="username")
	private String username;
	
	@Id
	@Column(name="authority")
	private String authority;

	public Authority() {
	}

	public Authority(String authority) {
		this.authority = authority;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "Authority [username=" + username + ", authority=" + authority + "]";
	}
	
	
}