package classfifteen.service.impl;

import classfifteen.entity.dto.OrderDTO;
import classfifteen.mapper.OrderMapper;
import classfifteen.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public int add(OrderDTO orderDTO) {
        return orderMapper.insert(orderDTO);
    }

    @Override
    public int update(OrderDTO orderDTO) {
        return 0;
    }

    @Override
    public int delete(OrderDTO orderDTO) {
        return 0;
    }

    @Override
    public OrderDTO selectOne(OrderDTO orderDTO) {
        return orderMapper.selectOne(orderDTO);
    }
}
