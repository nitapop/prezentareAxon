package axsonftpresentation.junitbestpractices.business;

import axsonftpresentation.junitbestpractices.model.MathState;
import axsonftpresentation.junitbestpractices.model.StudentDet;

public interface StudentBusiness {

    StudentDet getStudentById(Long id);

    MathState getMathState(Boolean hasDisertation, Boolean hasArticle);

    StudentDet getStudentByInf(Long id, String name);
}
