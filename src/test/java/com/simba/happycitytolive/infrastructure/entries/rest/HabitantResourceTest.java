package com.simba.happycitytolive.infrastructure.entries.rest;

import com.simba.happycitytolive.application.usecases.HabitantService;
import com.simba.happycitytolive.application.usecases.dto.HabitantEligible;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by podisto on 25/01/2022.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest
@ContextConfiguration(classes = {HabitantResource.class})
class HabitantResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HabitantService habitantService;

    @Test
    void getEligibleResidents_shouldReturnEmptyListHabitants() throws Exception {
        mockMvc.perform(get("/api/habitants/eligibles")).andExpect(status().isOk()).andExpect(content().string("[]"));
    }

    @Test
    void getEligibleResidents_shouldReturnListHabitants() throws Exception {
        List<HabitantEligible> habitantEligibles = initHabitantEligibles();
        given(habitantService.getEligibleHabitants()).willReturn(habitantEligibles);
        mockMvc.perform(get("/api/habitants/eligibles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom", is("Dione")))
                .andExpect(jsonPath("$[0].prenom", is("Omar")))
                .andExpect(jsonPath("$[1].nom", is("Doe")))
                .andExpect(jsonPath("$[1].prenom", is("Jane")));
    }

    private List<HabitantEligible> initHabitantEligibles() {
        List<HabitantEligible> habitantEligibles = new ArrayList<>();
        HabitantEligible omar = new HabitantEligible();
        omar.setNom("Dione");
        omar.setPrenom("Omar");
        omar.setEmail("omar@test.com");
        omar.setAdresse("test");

        HabitantEligible jane = new HabitantEligible();
        jane.setNom("Doe");
        jane.setPrenom("Jane");
        jane.setEmail("janedoe@test.com");
        jane.setAdresse("test");

        habitantEligibles.add(omar);
        habitantEligibles.add(jane);
        return habitantEligibles;
    }
}