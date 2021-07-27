package axsonftpresentation.junitbestpractices.general;

import axsonftpresentation.junitbestpractices.config.EntitiesTestSpringBootConfiguration;
import axsonftpresentation.junitbestpractices.domain.entities.StudentEty;
import axsonftpresentation.junitbestpractices.domain.entities.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        EntitiesTestSpringBootConfiguration.class,
        GeneralTest.Config.class})
@DataJpaTest(showSql = true)
public class GeneralTest {

    @Inject
    private StudentRepository studentRepository;

    @Test
    public void when_insertStudentWithSomeId_then_findStudent() {
        studentRepository.save(new StudentEty(1L, "Pop", null));

        Optional<StudentEty> expectedStudent = studentRepository.findStudentById(1L);

        assertThat("Pop").isEqualTo(expectedStudent.get().getName());
    }

    private StudentEty requestStudent(Long id) {
        studentRepository.save(new StudentEty(id, "Pop", null));
        Optional<StudentEty> actualStudent = studentRepository.findStudentById(id);
        if(actualStudent.isPresent()){
            return actualStudent.get();
        } else {
            return null;
        }
    }

    @Test
    public void studentExist_when_findById_then_foundSuccessfully() {
        StudentEty actualStudent = requestStudent(1L);

        StudentEty expectedStudent = new StudentEty(1L, "Pop", null);

        assertThat(actualStudent).isEqualTo(expectedStudent);
    }

    @Test
    public void when_insertStudentWithSomeId_then_findStudentWthSuccess() {
        studentRepository.save(new StudentEty(1L, "Pop", Date.from(Instant.ofEpochSecond(1200000l))));

        Optional<StudentEty> expectedStudent = studentRepository.findStudentById(1L);

        assertThat(1).isEqualTo(getMonthValue(expectedStudent.get().getBirthDate()));
    }

    private int getMonthValue(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getMonthValue();
    }

    @SpringBootConfiguration
    public static class Config {

    }


}
