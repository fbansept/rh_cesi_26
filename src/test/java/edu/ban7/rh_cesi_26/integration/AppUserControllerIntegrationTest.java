package edu.ban7.rh_cesi_26.integration;

import edu.ban7.rh_cesi_26.model.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class AppUserControllerIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                //.apply(springSecurity())
                .build();
    }

    @Test
    void callGetUserById_shouldReturn200() throws Exception {
        mvc.perform(get("/api/app-user/1"))
           .andExpect(status().isOk());
    }

    @Test
    void callGetNotExistingUserById_shouldReturn404() throws Exception {
        mvc.perform(get("/api/app-user/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void callGetUserById_shouldReturnUserWithoutPassword() throws Exception {
        mvc.perform(get("/api/app-user/1"))
                .andExpect(jsonPath("$.password").doesNotExist());
    }

    @Test
    void createUser_shouldReturn201() throws Exception {

        String json = "{\"email\":\"c@c.com\", \"password\":\"root\"}";

        MockHttpServletRequestBuilder requete = post("/api/app-user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(requete).andExpect(status().isCreated());
    }

    @Test
    void createUserWithoutMandatoryInformation_shouldReturn400() throws Exception {

        String json = "{\"email\":\"d@d.com\"}";

        MockHttpServletRequestBuilder requete = post("/api/app-user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(requete).andExpect(status().isBadRequest());

        json = "{\"email\":\"dd.com\", \"password\":\"root\"}";

        requete = post("/api/app-user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(requete).andExpect(status().isBadRequest());

        json = "{\"password\":\"root\"}";

        requete = post("/api/app-user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(requete).andExpect(status().isBadRequest());
    }



}
