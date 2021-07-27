package axsonftpresentation.junitbestpractices.config;

import axsonftpresentation.junitbestpractices.domain.EntitiesConfiguration;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@Import(EntitiesConfiguration.class)
public class EntitiesTestSpringBootConfiguration {

}
