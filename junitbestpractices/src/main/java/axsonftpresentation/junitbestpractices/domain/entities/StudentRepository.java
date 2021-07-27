package axsonftpresentation.junitbestpractices.domain.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.Optional;


public interface StudentRepository extends JpaRepository<StudentEty, Long> {

    @QueryHints(
            @QueryHint(name = "org.hibernate.cacheable", value = "true")
    )
    Optional<StudentEty> findStudentById(Long id);

}
