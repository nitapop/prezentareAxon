package axsonftpresentation.junitbestpractices.model;

import axsonftpresentation.junitbestpractices.domain.entities.StudentEty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Objects;

@JsonSerialize(as = StudentDet.class)
@JsonDeserialize(as = StudentDet.class)
public class StudentDet implements Serializable {
    private Long id;
    private String name;

    public StudentDet() {

    }

    public StudentDet(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentDet std = (StudentDet) o;
        return Objects.equals(id, std.id) &&
                Objects.equals(name, std.name);
    }

}
