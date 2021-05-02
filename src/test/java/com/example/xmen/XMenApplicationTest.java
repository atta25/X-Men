package com.example.xmen;

import com.example.xmen.dao.Stat;
import com.example.xmen.detector.Detector;
import com.example.xmen.repository.ResultRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class XMenApplicationTest {
    @Autowired
    private WebApplicationContext appContext;
    private MockMvc mockMvc;
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private Detector detector;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.appContext).build();
        resultRepository.deleteAll();
        detector.loadCriteria();
    }

    @Test
    public void whenThereAreNoRequestsThereShouldBeNoStatistics() throws Exception {
        ResultActions result = mockMvc.perform(
                get("/stats/")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.count_mutant_dna").value(0))
                .andExpect(jsonPath("$.count_human_dna").value(0))
                .andExpect(jsonPath("$.ratio").value(0));

        byte[] content = result.andReturn().getResponse().getContentAsByteArray();

        Stat statResult = new ObjectMapper().readValue(content, Stat.class);
        assertEquals(0L, (long) statResult.getHumanCount());
        assertEquals(0L, (long) statResult.getMutantCount());
        assertEquals(0L, (long) statResult.getTotal());
        assertEquals(0.0, statResult.getRatio());
    }

    @Test
    public void whenItIsNotMutantItShouldReturnForbidden() throws Exception {
        String request = "{\n"+
                "\"dna\": " +
                "        [\"ACCTATC\",\n" +
                "         \"CTCACTT\",\n" +
                "         \"ACGCTAT\",\n" +
                "         \"ACCTACC\",\n" +
                "         \"CAATTCC\",\n" +
                "         \"CACCAAT\",\n" +
                "         \"CAACAAT\"" +
                "        ]\n" +
                "}";
        mockMvc.perform(
                post("/mutant/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(request.getBytes()))
                .andExpect(status().isForbidden());
    }

    @Test
    public void whenItIsMutantItShouldReturnOk() throws Exception {
        String request = "{\n"+
                "\"dna\": " +
                "        [\"AAAAATC\",\n" +
                "         \"CTCACTC\",\n" +
                "         \"ACGCTAC\",\n" +
                "         \"ACCTACC\",\n" +
                "         \"CAATTCC\",\n" +
                "         \"CACCAAT\",\n" +
                "         \"CAACAAT\"" +
                "        ]\n" +
                "}";
        mockMvc.perform(
                post("/mutant/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(request.getBytes()))
                .andExpect(status().isOk());
    }

    @Test
    public void whenThereAreRequestsThereShouldBeStatistics() throws Exception {
        String request1 = "{\n"+
                "\"dna\": " +
                "        [\"AAAAATC\",\n" +
                "         \"CTCACTC\",\n" +
                "         \"ACGCTAC\",\n" +
                "         \"ACCTACC\",\n" +
                "         \"CAATTCC\",\n" +
                "         \"CACCAAT\",\n" +
                "         \"CAACAAT\"" +
                "        ]\n" +
                "}";

        String request2 = "{\n"+
                "\"dna\": " +
                "        [\"ACCTATC\",\n" +
                "         \"CTCACTT\",\n" +
                "         \"ACGCTAT\",\n" +
                "         \"ACCTACC\",\n" +
                "         \"CAATTCC\",\n" +
                "         \"CACCAAT\",\n" +
                "         \"CAACAAT\"" +
                "        ]\n" +
                "}";

        mockMvc.perform(
                post("/mutant/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(request1.getBytes()));

        mockMvc.perform(
                post("/mutant/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(request2.getBytes()));

        ResultActions result = mockMvc.perform(
                get("/stats/")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.count_mutant_dna").value(1))
                .andExpect(jsonPath("$.count_human_dna").value(1))
                .andExpect(jsonPath("$.ratio").value(1.0));

        byte[] content = result.andReturn().getResponse().getContentAsByteArray();

        Stat statResult = new ObjectMapper().readValue(content, Stat.class);
        assertEquals(1L, (long) statResult.getHumanCount());
        assertEquals(1L, (long) statResult.getMutantCount());
        assertEquals(1.0, statResult.getRatio());
    }
}
