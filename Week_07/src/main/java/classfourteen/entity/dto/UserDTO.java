package classfourteen.entity.dto;

import classfourteen.entity.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class UserDTO extends BaseDTO {

    private String userId;

    private String userName;

    private String userPwd;
}
