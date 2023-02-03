package com.ducdmd152.springboot.dsnackerstore.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository
	extends JpaRepository<User, String> {

}
