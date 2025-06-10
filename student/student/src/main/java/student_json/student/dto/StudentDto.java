package student_json.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@Data
@AllArgsConstructor
public class StudentDto {

    private String name;
    private int age;

    public ArrayList<String> classes;

    public StudentDto(Student student) {
        this.name = student.getName();
        this.age = student.getAge();
        this.classes = student.getClasses();
    }
}
