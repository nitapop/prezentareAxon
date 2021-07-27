package axsonftpresentation.junitbestpractices.database;

import axsonftpresentation.junitbestpractices.config.DataJpaTestsBase;
import axsonftpresentation.junitbestpractices.config.EntitiesTestSpringBootConfiguration;
import axsonftpresentation.junitbestpractices.domain.entities.StudentEty;
import axsonftpresentation.junitbestpractices.domain.entities.StudentRepository;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = EntitiesTestSpringBootConfiguration.class)
public class StudentEntityTest extends DataJpaTestsBase {

    @Inject
    private StudentRepository studentRepository;

    @Test
    public void when_testFindStudentById_then_success() {
        studentRepository.save(createStudent(1L, "Pop"));

        Optional<StudentEty> expectedStudent = studentRepository.findStudentById(1L);

        assertThat("Pop").isEqualTo(expectedStudent.get().getName());
    }

    private StudentEty createStudent(Long id, String name){
        return new StudentEty(id, name, null);
    }

    @Test
    @DatabaseSetup(value="/xml/StudentEntityTest-01-i.xml")
    public void databaseEx_when_testFindStudentById_then_success() {
        final Optional<StudentEty> expectedStudent = studentRepository.findStudentById(1L);

        assertThat("Nita Pop").isEqualTo(expectedStudent.get().getName());
    }

}
