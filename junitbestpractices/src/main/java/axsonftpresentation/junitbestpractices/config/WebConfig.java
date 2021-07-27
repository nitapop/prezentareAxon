package axsonftpresentation.junitbestpractices.config;

import axsonftpresentation.junitbestpractices.controller.StudentController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses= StudentController.class)
public class WebConfig {

}