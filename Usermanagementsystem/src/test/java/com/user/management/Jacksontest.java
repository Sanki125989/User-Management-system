package com.user.management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.management.model.User;
import com.user.management.repository.UserRepository;
import com.user.management.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Jacksontest {

	
	@LocalServerPort
    private int port;
    
    
    @MockBean
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;
    
    
    @Autowired
    private TestRestTemplate restTemplate;
    
	
    @Test
    public void testSaveUser() throws Exception {
        // Create a new user to save
        User user = new User();
        user.setFirstName("Uday");
        user.setLastName("Yadhav");
        user.setEmail("u@gmail.com");

        // Serialize the user object to JSON using Jackson
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);

        // Set up the request entity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(userJson, headers);

        // Send the POST request to the API endpoint
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/saveUserInJakson", requestEntity, String.class);

        // Verify the response status code and body
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User saved successfully", responseEntity.getBody());

        // Verify that the user was saved in the database
     User findByEmail = userRepository.findByEmail(user.getEmail());
        assertNotNull(findByEmail);
        assertEquals(user.getFirstName(), findByEmail.getFirstName());
        assertEquals(user.getLastName(), findByEmail.getLastName());
        assertEquals(user.getEmail(), findByEmail.getEmail());
    }


}
