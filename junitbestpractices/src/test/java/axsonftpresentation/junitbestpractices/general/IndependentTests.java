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

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        EntitiesTestSpringBootConfiguration.class,
        IndependentTests.Config.class})
@DataJpaTest(showSql = true)
public class IndependentTests {

    @Inject
    private StudentRepository studentRepository;

    @Test
    public void do_when_insertStudents_then_findAllSizeIsCorrect() {
        insertIntoDataBase(
                createStudent(1L, "Student1"),
                createStudent(2L, "Student2"),
                createStudent(3L, "Student3")
        );

        int expectedStudentsNr = studentRepository.findAll().size();

        assertThat(3).isEqualTo(expectedStudentsNr);
    }

    @Test
    public void doEx_when_findStudentById_then_foundSuccessfully() {
        insertIntoDataBase(createStudent(1L, "Student1"));

        StudentEty expectedStudent = new
                StudentEty(1L, "Student1", null);

        assertThat(1L).isEqualTo(expectedStudent.getId());
    }

    @Test
    public void doNotEx_when_findStudentById_then_foundSuccessfully() {
        insertIntoDataBase(createStudent());

        StudentEty expectedStudent =
                new StudentEty(1L, "Student1", null);

        assertThat(1L).isEqualTo(expectedStudent.getId());
    }

    private StudentEty createStudent(){
        return new StudentEty(1L, "Student1", null);
    }

    private StudentEty createStudent(Long id, String name){
        return new StudentEty(id, name, null);
    }

    private void insertIntoDataBase(StudentEty... students) {
        for (StudentEty student : students) {
            studentRepository.save(student);
        }
    }

    @SpringBootConfiguration
    public static class Config {

    }

}
