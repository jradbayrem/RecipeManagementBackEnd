package com.controller;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.DAO.UserDaoTest;
import com.entity.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.UserService;
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserControllerTest {
    
    @Autowired
    private UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoTest.class);
    
    @Autowired
    WebApplicationContext webApplicationContext;
    
    protected MockMvc mvc;
    
    @Before
    public final void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockitoAnnotations.initMocks(this);
        }
    protected String uri;
    
    public UserControllerTest() {
        this.uri = "/user";   
    }
    
    protected final String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    
    protected final <T> T mapFromJson(String json, Class<T> clazz)
    throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json,  clazz);
    }
    
    @Test
    public void getAllEntityList() {
        MvcResult mvcResult;
        try {
            LOGGER.info("------------Testing getAllEntity Method------------");
            LOGGER.info("------------Constructing Utilisateur ------------");
            LOGGER.info("------------Saving Utilisateur ------------");
            
            userService.addUser(new User(2, "dalii"));
            
            LOGGER.info("------------Mocking Context Webservice ------------");
            mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri + "/all").accept(MediaType.APPLICATION_JSON_VALUE))
                        .andReturn();
            
            LOGGER.info("------------Getting HTTP Status ------------");
            int status = mvcResult.getResponse().getStatus();
            
            LOGGER.info("------------Verifying HTTP Status ------------");
            assertEquals(200, status);
            
            LOGGER.info("------------Getting HTTP Response ------------");
            String content = mvcResult.getResponse().getContentAsString();
            
            LOGGER.info("------------ Deserializing JSON Response ------------");
            User[] userList = this.mapFromJson(content, User[].class);
            assertTrue(userList.length>0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void createEntity() {
        LOGGER.info("------------Testing createEntity Method------------");
        LOGGER.info("------------Constructing Utilisateur ------------");
        
        User user = new User(50, "sala7");
        
        MvcResult mvcResult;
        try {
            
            LOGGER.info("------------ Serializing Utilisateur Object ------------");
            String inputJson = this.mapToJson(user);
            
            LOGGER.info("------------Mocking Context Webservice and Invoking the Webservice ------------");
            mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri + "/adduser")
                    .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
            
            LOGGER.info("------------Getting HTTP Status ------------");
            int status = mvcResult.getResponse().getStatus();
            
            LOGGER.info("------------Verifying HTTP Status ------------");
            assertEquals(200, status);
            
            LOGGER.info("------------Searching for Utilisateur ------------");
            User userFound = userService.getUserById(new Long(50));
            
            LOGGER.info("------------ Verifying Utilisateur ------------");
            assertEquals(userFound.getUserName(), user.getUserName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void updateEntity() {
        try {
            LOGGER.info("------------Testing updateEntity Method------------");
            LOGGER.info("------------Constructing Utilisateur ------------");
            User oldUser = new User(2, "Lemon");
            
            LOGGER.info("------------Saving Utilisateur ------------");
            userService.addUser(oldUser);
            
            LOGGER.info("------------Modifying Utilisateur ------------");
            User newUser = new User(2, "Lemonade");
            
            LOGGER.info("------------ Serializing Utilisateur Object ------------");
            String inputJson = this.mapToJson(newUser);
            
            LOGGER.info("------------Mocking Context Webservice and Invoking the Webservice ------------");
            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri + "/2")
                    .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
            
            LOGGER.info("------------Getting HTTP Status ------------");
            int status = mvcResult.getResponse().getStatus();
            
            LOGGER.info("------------Verifying HTTP Status ------------");
            assertEquals(200, status);
            
            LOGGER.info("------------Searching for Utilisateur ------------");
            User userFound = userService.getUserById(new Long(2));
            
            LOGGER.info("------------ Verifying Utilisateur ------------");
            assertEquals(userFound.getUserName(), newUser.getUserName());
    }
    catch (Exception e) {
        e.printStackTrace();
    }
    }
    
    @Test
    public void deleteEntity() {
        LOGGER.info("------------Testing deleteEntity Method------------");
        try{
        LOGGER.info("------------Constructing Utilisateur ------------");
        LOGGER.info("------------Saving Utilisateur ------------");
        userService.addUser(new User(2, "lemon"));
        
        LOGGER.info("------------Mocking Context Webservice and Invoking the Webservice ------------");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri + "/2")).andReturn();
        
        LOGGER.info("------------Getting HTTP Status ------------");
        int status = mvcResult.getResponse().getStatus();
        
        LOGGER.info("------------Verifying HTTP Status ------------");
        assertEquals(200, status);
        
        LOGGER.info("------------Searching for Utilisateur ------------");
        User userFound = userService.getUserById(new Long(2));
        
        LOGGER.info("------------ Verifying Utilisateur ------------");
        assertEquals(userFound, null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
}