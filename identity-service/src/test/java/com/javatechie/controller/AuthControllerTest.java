package com.javatechie.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatechie.dto.AuthRequest;
import com.javatechie.entity.UserCredential;
import com.javatechie.repository.UserCredentialRepository;
import com.javatechie.service.AuthService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.access.intercept.RunAsUserToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AuthController.class})
@ExtendWith(SpringExtension.class)
class AuthControllerTest {
    @Autowired
    private AuthController authController;

    @MockBean
    private AuthService authService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private UserCredentialRepository userCredentialRepository;

    /**
     * Method under test: {@link AuthController#addNewUser(UserCredential)}
     */
    @Test
    void testAddNewUser() throws Exception {
        when(userCredentialRepository.existsByName(Mockito.<String>any())).thenReturn(true);

        UserCredential userCredential = new UserCredential();
        userCredential.setEmail("jane.doe@example.org");
        userCredential.setId(1);
        userCredential.setName("Name");
        userCredential.setPassword("iloveyou");
        userCredential.setRole_name("Role name");
        String content = (new ObjectMapper()).writeValueAsString(userCredential);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"message\":\"User already exists\",\"status\":\"error\"}"));
    }

    /**
     * Method under test: {@link AuthController#validateToken(String)}
     */
    @Test
    void testValidateToken() throws Exception {
        doNothing().when(authService).validateToken(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/validate").param("token", "foo");
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Token is valid"));
    }

    /**
     * Method under test: {@link AuthController#addNewUser(UserCredential)}
     */
    @Test
    void testAddNewUser2() throws Exception {
        when(userCredentialRepository.existsByName(Mockito.<String>any())).thenReturn(false);
        when(authService.saveUser(Mockito.<UserCredential>any())).thenReturn("Save User");

        UserCredential userCredential = new UserCredential();
        userCredential.setEmail("jane.doe@example.org");
        userCredential.setId(1);
        userCredential.setName("Name");
        userCredential.setPassword("iloveyou");
        userCredential.setRole_name("Role name");
        String content = (new ObjectMapper()).writeValueAsString(userCredential);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"message\":\"User added to the system\",\"status\":\"success\"}"));
    }

    /**
     * Method under test: {@link AuthController#getAllUsers()}
     */
    @Test
    void testGetAllUsers() throws Exception {
        when(userCredentialRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/all");
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AuthController#getToken(AuthRequest)}
     */
    @Test
    void testGetToken() throws Exception {
        when(authService.generateToken(Mockito.<String>any())).thenReturn("ABC123");
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        Class<Authentication> originalAuthentication = Authentication.class;
        when(authenticationManager.authenticate(Mockito.<Authentication>any())).thenReturn(
                new RunAsUserToken("invalid access", "Principal", "Credentials", authorities, originalAuthentication));

        AuthRequest authRequest = new AuthRequest();
        authRequest.setPassword("iloveyou");
        authRequest.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(authRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"token\":\"ABC123\"}"));
    }
}

