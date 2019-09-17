package com.DAO;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	public User findById(Long id);

}
