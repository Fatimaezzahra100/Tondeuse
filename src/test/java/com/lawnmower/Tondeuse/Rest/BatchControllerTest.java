package com.lawnmower.Tondeuse.Rest;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.core.io.Resource;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@SpringBatchTest
@AutoConfigureMockMvc
@ContextConfiguration
class BatchControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;


    @Test
    public void testExecuteBatch() throws Exception {
        // Perform the HTTP GET request to execute the batch job
        mockMvc.perform(MockMvcRequestBuilders.get("/startJob"))
                .andExpect(status().isOk())
                .andReturn();


    }

}