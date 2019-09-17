package com.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.DAO.UserDaoTest;
import com.entity.User;



@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@Autowired
    private UserService userService;
	
	@Autowired
    @Mock
    private UserService userServiceToMock;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoTest.class);
	
	@Before
	public final void setUp() {			//Dans ce test là on va utiliser les annotatiosn de Mockito
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void givenUsers_givenHalfOfNumber() {
		LOGGER.info("---------------------- Testing givenUsers_givenHalfOfNumber Method ---------------");
		LOGGER.info("---------------------- Constructing Utilisateur ---------------");
		//userServiceToMock = Mockito.mock(UserService.class); Use can use this instead of using annotation
		LOGGER.info("---------------------- Mocking getAll() methode of IUtilisateurService ---------------");
		Mockito.when(userServiceToMock.getAllUsers()).thenReturn(Arrays.asList(new User(10,"dalii"),
			new User(1,"dalii"), new User(2,"dalii"), new User(18,"dalii")));
		LOGGER.info("---------------------- Method Mockedd ---------------");
		LOGGER.info("---------------------- Verifying Result ---------------");
		assertEquals(2, userService.getUserNbrHalf(userServiceToMock.getAllUsers()));	
	}
}
