package med.voll.api.controller;

import med.voll.api.domain.adress.Adress;
import med.voll.api.domain.adress.AdressData;
import med.voll.api.domain.medic.*;
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

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicControllerTest {

    @Autowired
    private JacksonTester<DataStoreMedic> dataStoreMedicJson;

    @Autowired
    private JacksonTester<DataDetailsMedic> dataDetailsMedicJson;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicRepository medicRepository;

    @Test
    @DisplayName("Should return http code 400 when information is invalid")
    @WithMockUser
    public void saveScene1() throws Exception {
        var response = mockMvc.perform(post("/medics"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return http code 200 when information is valid")
    @WithMockUser
    public void saveScene2() throws Exception {
        var dataSave = new DataStoreMedic(
                "Medic",
                "medic@voll.med",
                "86999999999",
                "123456",
                Specialty.CARDIOLOGY,
                dataAdress()
        );

        when(medicRepository.save(any())).thenReturn(new Medic(dataSave));

        var response = mockMvc.perform(
                post("/medics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataStoreMedicJson.write(dataSave).getJson()))
                .andReturn().getResponse();

        var jsonReturn = dataDetailsMedicJson.write(new DataDetailsMedic(
                null,
                dataSave.name(),
                dataSave.phone(),
                dataSave.email(),
                dataSave.specialty(),
                new Adress(dataSave.adress())
        )).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonReturn);
    }

    private AdressData dataAdress() {
        return new AdressData(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}