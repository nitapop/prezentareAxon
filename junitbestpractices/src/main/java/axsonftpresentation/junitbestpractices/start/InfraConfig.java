package axsonftpresentation.junitbestpractices.start;

import axsonftpresentation.junitbestpractices.config.BusinessConfig;
import axsonftpresentation.junitbestpractices.config.WebConfig;
import axsonftpresentation.junitbestpractices.domain.EntitiesConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        EntitiesConfiguration.class,
        BusinessConfig.class,
        WebConfig.class

})
public class InfraConfig {
}
