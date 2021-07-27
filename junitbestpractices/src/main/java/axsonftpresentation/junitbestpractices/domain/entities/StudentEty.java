package axsonftpresentation.junitbestpractices.domain.entities;

import axsonftpresentation.junitbestpractices.model.MathState;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "STUDENT")
public class StudentEty {

    @Id
    private Long id;

    private String name;

    private Date birthDate;

    private MathState mathState;


    public StudentEty (){

    }

    public StudentEty (Long id, String name, Boolean hasDisertation, Boolean hasArticle){
        this.id = id;
        this.name = name;
        this.mathState = MathState.ACCEPTED;
    }

    public StudentEty(Long id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public MathState getMathState() {
        return mathState;
    }

    public void setMathState(MathState mathState) {
        this.mathState = mathState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDate, mathState);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentEty std = (StudentEty) o;
        return Objects.equals(id, std.id) &&
                Objects.equals(name, std.name) &&
                Objects.equals(birthDate, std.birthDate) &&
                Objects.equals(mathState, std.mathState);
    }
}