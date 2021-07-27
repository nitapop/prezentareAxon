package axsonftpresentation.junitbestpractices.domain;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfigurationPackage
@EnableJpaRepositories
@EntityScan
public class EntitiesConfiguration {

}
