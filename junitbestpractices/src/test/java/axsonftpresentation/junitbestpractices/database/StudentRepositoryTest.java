package axsonftpresentation.junitbestpractices.database;

import axsonftpresentation.junitbestpractices.config.EntitiesTestSpringBootConfiguration;
import axsonftpresentation.junitbestpractices.domain.entities.StudentEty;
import axsonftpresentation.junitbestpractices.domain.entities.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = EntitiesTestSpringBootConfiguration.class)
public class StudentRepositoryTest {

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

}
