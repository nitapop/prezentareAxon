package axsonftpresentation.junitbestpractices.config;


import axsonftpresentation.junitbestpractices.business.StudentBusinessImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = StudentBusinessImpl.class)
public class BusinessConfig {

}