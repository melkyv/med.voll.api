package med.voll.api.controller;

import med.voll.api.domain.consultation.ConsultationService;
import med.voll.api.domain.consultation.DataDetailsConsultation;
import med.voll.api.domain.consultation.DataSchedulingConsultation;
import med.voll.api.domain.medic.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DataSchedulingConsultation> dataSchedulingConsultationJson;

    @Autowired
    private JacksonTester<DataDetailsConsultation> dataDetailsConsultationJson;

    @MockBean
    private ConsultationService consultationService;

    @Test
    @DisplayName("Should return http code 400 when information is invalid")
    @WithMockUser
    void scheduleScene1() throws Exception {
        var response = mockMvc.perform(post("/consultations"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return http code 200 when information is valid")
    @WithMockUser
    void scheduleScene2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGY;

        var consultationsDetails = new DataDetailsConsultation(null, 2l, 5l, date);
        when(consultationService.schedule(any())).thenReturn(consultationsDetails);

        var response = mockMvc.perform(
                post("/consultations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataSchedulingConsultationJson.write(
                                new DataSchedulingConsultation(2l, 5l, date, specialty))
                                .getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonReturn = dataDetailsConsultationJson.write(
                consultationsDetails).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonReturn);
    }
}