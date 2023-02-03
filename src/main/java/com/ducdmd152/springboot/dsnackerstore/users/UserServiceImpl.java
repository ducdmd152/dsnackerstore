package com.ducdmd152.springboot.dsnackerstore.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserJpaRepository userJpaRepositoy;
	
	@Override
	public void saveUser(User user) {
		userJpaRepositoy.save(user);
	}

	@Override
	public boolean checkExist(String username) {
		return userJpaRepositoy.existsById(username);
	}
	
}
