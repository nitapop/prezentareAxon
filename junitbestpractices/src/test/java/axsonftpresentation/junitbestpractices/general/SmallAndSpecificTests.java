package axsonftpresentation.junitbestpractices.general;

import axsonftpresentation.junitbestpractices.config.EntitiesTestSpringBootConfiguration;
import axsonftpresentation.junitbestpractices.domain.entities.StudentEty;
import axsonftpresentation.junitbestpractices.domain.entities.StudentRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        EntitiesTestSpringBootConfiguration.class,
        IndependentTests.Config.class})
@DataJpaTest(showSql = true)
public class SmallAndSpecificTests {

    @Inject
    private StudentRepository studentRepository;

    @Test
    public void donotEx_when_insertStudents_then_findAllSizeIsCorrect() {
        List<StudentEty> students = Lists.newArrayList(
                new StudentEty(1L, "Student1", null),
                new StudentEty(2L, "Student2", null),
                new StudentEty(3L, "Student3", null)
        );

        for (StudentEty student : students) {
            studentRepository.save(student);
        }

        int expectedStudentsNr = studentRepository.findAll().size();

        assertThat(3).isEqualTo(expectedStudentsNr);
    }

    @Test
    public void doEx_when_insertStudents_then_findAllSizeIsCorrect() {
        insertIntoDataBase(
                createStudent(1L, "Student1"),
                createStudent(2L, "Student2"),
                createStudent(3L, "Student3")
        );

        int expectedStudentsNr = studentRepository.findAll().size();

        assertThat(3).isEqualTo(expectedStudentsNr);
    }

    @Test
    public void when_findStudentByInf_then_foundSuccessfully() {
        List<StudentEty> studentsLst = insertStudentsIntoDataBase(
                createStudent(1L, "Student1"),
                createStudent(2L, "Student2"),
                createStudent(3L, "Student3")
        );

        StudentEty expectedStudent = new StudentEty(1L, "Student1", null);

        assertThat(true).isEqualTo(studentsLst.contains(expectedStudent));

    }

    private StudentEty createStudent(Long id, String name){
        return new StudentEty(id, name, null);
    }

    private void insertIntoDataBase(StudentEty... students) {
        for (StudentEty student : students) {
            studentRepository.save(student);
        }
    }


    @Test
    public void doNotVrbEx_when_insertStudents_then_findAllSizeIsCorrect() {
        Long id1 = 1L;
        Long id2 = 2L;
        Long id3 = 3L;
        insertIntoDataBase(
                createStudent(id1, "Student1"),
                createStudent(id2, "Student2"),
                createStudent(id3, "Student3")
        );

        int expectedStudentsNr = studentRepository.findAll().size();

        assertThat(3).isEqualTo(expectedStudentsNr);
    }

    private List<StudentEty> insertStudentsIntoDataBase(StudentEty... students) {
        List<StudentEty> studentsLst = Lists.newArrayList();
        for (StudentEty student : students) {
            studentsLst.add(studentRepository.save(student));
        }
        return studentsLst;
    }

    @Test
    public void doNotMixEx_when_insertStudents_then_findAllSizeIsCorrect() {
        List<StudentEty> studentsLst = insertStudentsIntoDataBase(
                createStudent(1L, "Student1"),
                createStudent(2L, "Student2"),
                createStudent(3L, "Student3")
        );

        int expectedStudentsNr = studentRepository.findAll().size();

        assertThat(3).isEqualTo(expectedStudentsNr);

        StudentEty expectedStudent = new StudentEty
                (1L, "Student1", null);

        assertThat(true).isEqualTo(studentsLst.contains(expectedStudent));
    }

    @SpringBootConfiguration
    public static class Config {

    }

}
