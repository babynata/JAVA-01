package classnine.homework_02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private int id;

    private String name;

    private String classNo;

    public void info() {
        System.out.println("student-->id:[" + id + "],name:[" + name + "],classNo:[" + classNo + "]");
    }
}
