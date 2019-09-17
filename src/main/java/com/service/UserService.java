package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DAO.UserDao;
import com.entity.User;

@Service
public class UserService {

	@Autowired			//Injection de userDao
	UserDao userDao;
	
	public List<User> getAllUsers() {
        return this.userDao.findAll();
    }
	
	public User addUser(User user) {
        return this.userDao.save(user);
    }
	
	public User getUserById(Long userId) {
        return this.userDao.findById(userId);
    }
	
	public void updateUser(User utilisateur) {
        this.userDao.save(utilisateur);
    }
	
	public void deleteUser(User utilisateur) {
        this.userDao.delete(utilisateur);
    }
	
	public int getUserNbrHalf(List<User> users) {
        System.out.println("*******************" + users.size() / 2);
        return users.size() / 2;
    }
}
