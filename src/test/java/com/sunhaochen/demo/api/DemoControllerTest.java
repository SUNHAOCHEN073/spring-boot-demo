package com.sunhaochen.demo.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void helloReturnsGreeting() throws Exception {
        mockMvc.perform(get("/api/hello").param("name", "孙浩宸"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello, 孙浩宸!"));
    }

    @Test
    void timeReturnsIsoTimestamp() throws Exception {
        mockMvc.perform(get("/api/time"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.iso").exists())
                .andExpect(jsonPath("$.epochMillis").isNumber());
    }

    @Test
    void echoReturnsBody() throws Exception {
        mockMvc.perform(post("/api/echo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"ping\":\"pong\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.received").value("{\"ping\":\"pong\"}"))
                .andExpect(jsonPath("$.length").value("{\"ping\":\"pong\"}".length()));
    }
}
