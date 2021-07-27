package axsonftpresentation.junitbestpractices.web;

import axsonftpresentation.junitbestpractices.business.StudentBusiness;
import axsonftpresentation.junitbestpractices.controller.StudentController;
import axsonftpresentation.junitbestpractices.model.StudentDet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.easymock.EasyMockRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ContextConfiguration(classes = WebMvcStudentTest.Config.class)

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class WebMvcStudentTest {

    @Inject
    private WebApplicationContext context;

    @MockBean
    private StudentBusiness studentBusiness;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void when_getStudentInf_then_infIsForRqStudent() throws Exception {
        when(studentBusiness.getStudentById(Mockito.anyLong()))
                .thenReturn(new StudentDet(1L, "Pop"));

        String expectedResult = mockMvc.perform(get("/studentInf")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", "1"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(jsonToDet(expectedResult))
                .extracting(StudentDet::getId)
                .containsOnly(1L);
    }

    private Collection<StudentDet> jsonToDet(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        return mapper.readValue(json, new TypeReference<Collection<StudentDet>>(){});
    }


    @SpringBootConfiguration
    public static class Config {

        @Bean
        public StudentController studentController() {
            return new StudentController();
        }

    }


}
