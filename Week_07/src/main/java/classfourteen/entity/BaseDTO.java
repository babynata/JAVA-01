package classfourteen.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseDTO implements Serializable{

    private int id;

    private int status;

    private String remark;

    private String createdBy;

    private LocalDateTime dateCreated;

    private String updatedBy;

    private LocalDateTime dateUpdated;
}
