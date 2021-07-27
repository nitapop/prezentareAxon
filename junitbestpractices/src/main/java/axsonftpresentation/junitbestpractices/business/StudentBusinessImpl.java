package axsonftpresentation.junitbestpractices.business;

import axsonftpresentation.junitbestpractices.domain.entities.StudentEty;
import axsonftpresentation.junitbestpractices.domain.entities.StudentRepository;
import axsonftpresentation.junitbestpractices.model.MathState;
import axsonftpresentation.junitbestpractices.model.StudentDet;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class StudentBusinessImpl implements StudentBusiness {

    @Inject
    private StudentRepository studentRepository;

    @Override
    public StudentDet getStudentById(Long id) {
        return mapEtyToModel(new StudentEty(1L, "Pop", null));
    }

    @Override
    public MathState getMathState(Boolean hasDisertation, Boolean hasArticle) {
        return MathState.ACCEPTED;
    }

    @Override
    public StudentDet getStudentByInf(Long id, String name) {
        return mapEtyToModel(studentRepository.findStudentById(id).get());
    }


    private StudentDet mapEtyToModel(StudentEty stdEty) {
        StudentDet std = new StudentDet();
        std.setId(stdEty.getId());
        std.setName(stdEty.getName());
        return std;
    }
}
