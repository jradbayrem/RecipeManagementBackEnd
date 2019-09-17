package com.DAO;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    
    @Autowired
   private UserDao userDao;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoTest.class);
    
    @Test
    public void givenEntityRepository_whenSaving() {
        LOGGER.info("-----------Testing givenEntityRepository_whenSaving Method ------------");
        User addedUser = userDao.save(new User(10, "dalii"));
        LOGGER.info("------------Utilisateur saved-----------");
        LOGGER.info("------------Searching for Utilisateur-----------");
        User foundUser = userDao.findById(addedUser.getId());
        LOGGER.info("----------Utilisateur found------------");
        LOGGER.info("------------Verifying Utilisateur ------------");
        assertNotNull(foundUser);
        assertEquals(addedUser.getUserName(), foundUser.getUserName());
        LOGGER.info("------------Utilisateur verified -----------");
    }
    

    @Test
    public void givenEntityRepository_whenUpdating() {
        LOGGER.info("-----------Testing upadateUser method ------------");
        User addedUser = userDao.save(new User(11, "Bob"));
        LOGGER.info("------------Utilisateur saved-----------");
        LOGGER.info("------------Updating Utilisateur ------------");
        addedUser.setUserName("Marie");
        addedUser = userDao.save(addedUser);
        LOGGER.info("------------Updated Utilisateur ------------");
        LOGGER.info("------------Verifiyng Utilisateur ------------");
        User foundUser = userDao.findById(addedUser.getId());
        assertNotEquals("Bob", foundUser.getUserName());
        LOGGER.info("------------Utilisateur verified -----------");
    }
    
    @Test
    public void givenEntityRepository_whenDeleting() {
        LOGGER.info("-----------Testing givenEntityRepository_whenSaving Method ------------");
        User addedUser = userDao.save(new User(12, "A Supprimer"));
        LOGGER.info("------------Utilisateur saved-----------");
        LOGGER.info("------------Deleting Utilisateur-----------");
        userDao.delete(addedUser);
        LOGGER.info("----------Searching deleted user------------");
        User foundUser = userDao.findById(addedUser.getId());
        LOGGER.info("------------Verifying Utilisateur ------------");        
        assertNull(foundUser);
        LOGGER.info("------------Utilisateur verified -----------");
    }
    
    @Test
    public void givenEntityRepository_whenGettingById() {
        LOGGER.info("-----------Testing givenEntityRepository_whenSaving Method ------------");
        User addedUser = userDao.save(new User(13, "To get"));
        LOGGER.info("------------Utilisateur saved-----------");
        LOGGER.info("------------Searching for Utilisateur-----------");
        User foundUser = userDao.findById(addedUser.getId());
        LOGGER.info("----------Utilisateur found------------");
        LOGGER.info("------------Verifying Utilisateur ------------");
        assertNotNull(foundUser);
        assertEquals(addedUser.getUserName(), foundUser.getUserName());
        LOGGER.info("------------Utilisateur verified -----------");
    }
}