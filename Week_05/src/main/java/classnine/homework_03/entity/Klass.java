package classnine.homework_03.entity;

import lombok.Data;


import java.util.List;

@Data
public class Klass {

    Student student;
    
    List<Student> students;
    
    public void dong(){
        System.out.println(this.getStudent());
    }
    
}
