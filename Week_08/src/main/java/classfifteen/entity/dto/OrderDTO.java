package classfifteen.entity.dto;

import classfifteen.entity.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class OrderDTO extends BaseDTO {

    private int orderId;

    private int userId;

    private String expressId;

    private LocalDateTime orderDate;

}
