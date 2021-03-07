package classfourteen.service.impl;

import classfourteen.BaseTest;
import classfourteen.entity.dto.UserDTO;
import classfourteen.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;

public class UserServiceImplTest extends BaseTest {

    @Resource
    private UserService userService;

    @Test
    public void add() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId("u_000002");
        userDTO.setUserName("Nata");
        userDTO.setUserPwd("12345");
        userDTO.setStatus(0);
        int count = userService.add(userDTO);
        System.out.println(count + "has added");
    }

    @Test
    public void selectOne() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO = userService.selectOne(userDTO);
        System.out.println(userDTO);
    }
}