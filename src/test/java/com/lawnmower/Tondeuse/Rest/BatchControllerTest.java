package com.lawnmower.Tondeuse.Rest;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.support.PropertiesConverter;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@PropertySource("classpath:application-test.properties")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@SpringBatchTest
@AutoConfigureMockMvc
@ContextConfiguration
class BatchControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testExecuteBatch() throws Exception {
        // Perform the HTTP GET request to execute the batch job
        mockMvc.perform(MockMvcRequestBuilders.get("/startJob"))
                .andExpect(status().isOk()).andExpect(content().string("\"COMPLETED\""))
                .andReturn();
    }

}