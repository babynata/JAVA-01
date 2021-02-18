package classten.homework_03.entity;


import lombok.Data;

import mystarter.entity.Klass;
import mystarter.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Data
@Service
public class School implements ISchool {
    
    // Resource 
    @Autowired(required = true) //primary
            Klass class1;
    
    @Resource(name = "student100")
    Student student100;
    
    @Override
    public void ding(){
    
        System.out.println("Class1 have " + class1.getStudents().size() + " students and one is " + student100);
        
    }
    
}
