package classfifteen.service.impl;

import classfifteen.BaseTest;
import classfifteen.entity.dto.OrderDTO;
import classfifteen.service.OrderService;
import org.junit.Test;

import javax.annotation.Resource;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class OrderServiceImplTest extends BaseTest {

    @Resource
    private OrderService orderService;

    @Test
    public void add() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(3);
        orderDTO.setUserId(3);
        orderDTO.setExpressId("e_000001");
        orderDTO.setOrderDate(LocalDateTime.now());
        orderDTO.setStatus(0);
        orderService.add(orderDTO);
    }

}