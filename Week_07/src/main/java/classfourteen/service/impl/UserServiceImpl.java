package classfourteen.service.impl;

import classfourteen.entity.dto.UserDTO;
import classfourteen.mapper.UserMapper;
import classfourteen.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public int add(UserDTO userDTO) {
        return userMapper.insert(userDTO);
    }

    @Override
    public int update(UserDTO userDTO) {
        return 0;
    }

    @Override
    public int delete(UserDTO userDTO) {
        return 0;
    }

    @Override
    public UserDTO selectOne(UserDTO userDTO) {
        return userMapper.selectOne(userDTO);
    }
}
