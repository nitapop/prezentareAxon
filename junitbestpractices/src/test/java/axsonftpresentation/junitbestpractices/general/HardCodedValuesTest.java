package axsonftpresentation.junitbestpractices.general;

import axsonftpresentation.junitbestpractices.business.StudentBusiness;
import axsonftpresentation.junitbestpractices.config.BusinessConfig;
import axsonftpresentation.junitbestpractices.config.DataJpaTestsBase;
import axsonftpresentation.junitbestpractices.config.EntitiesTestSpringBootConfiguration;
import axsonftpresentation.junitbestpractices.controller.StudentController;
import axsonftpresentation.junitbestpractices.domain.entities.StudentEty;
import axsonftpresentation.junitbestpractices.domain.entities.StudentRepository;
import axsonftpresentation.junitbestpractices.model.MathState;
import axsonftpresentation.junitbestpractices.model.StudentDet;
import axsonftpresentation.junitbestpractices.web.WebMvcStudentTest;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        EntitiesTestSpringBootConfiguration.class,
        BusinessConfig.class,
        HardCodedValuesTest.Config.class})
@DataJpaTest(showSql = true)
public class HardCodedValuesTest {

    @Inject
    private StudentRepository studentRepository;
    @Inject
    private StudentBusiness studentBusiness;

    @Before
    public  void setUp(){
        studentRepository.deleteAll();
    }

    @Test
    public void doNotEx_when_checkMathState_then_mathStateAccepted() {
        studentRepository.save(new StudentEty(2L, "Pop", true, true));
        Optional<StudentEty> actualStudent = studentRepository.findStudentById(2L);

        MathState expectedState = studentBusiness.getMathState(true, true);

        assertThat(actualStudent.get().getMathState()).isEqualTo(expectedState);
    }

    @Test
    public void doEx_when_checkMathState_then_mathStateAccepted() {
        studentRepository.save(new StudentEty(2L,"Pop", true, true));
        Optional<StudentEty> actualStudent = studentRepository.findStudentById(2L);

        assertThat(actualStudent.get().getMathState()).isEqualTo(MathState.ACCEPTED);
    }

    @Test
    public void doNotEx_when_getStudentByInf_then_foundStudentSuccessfully() {
        StudentEty input = studentRepository.save(new StudentEty(2L, "Pop", null));
        StudentDet actualStudent = studentBusiness.getStudentByInf(2L, "Pop");

        StudentDet expectedStudent = mapEtyToModel(input);

        assertThat(actualStudent).isEqualTo(expectedStudent);
    }

    private StudentDet mapEtyToModel(StudentEty stdEty) {
        StudentDet std = new StudentDet();
        std.setId(stdEty.getId());
        std.setName(stdEty.getName());
        return std;
    }

    @Test
    public void doEx_when_getStudentByInf_then_foundStudentSuccessfully() {
        studentRepository.save(new StudentEty(2L, "Pop", null));

        StudentDet actualStudent = studentBusiness.getStudentByInf(2L, "Pop");

        assertThat(actualStudent).isEqualTo(new StudentDet(2L, "Pop"));
    }


    @SpringBootConfiguration
    public static class Config {

    }

}
