package com.ducdmd152.springboot.dsnackerstore.users;

public interface UserService {
	public void saveUser(User user);

	public boolean checkExist(String username);
}
