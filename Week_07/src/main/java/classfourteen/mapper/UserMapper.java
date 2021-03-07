package classfourteen.mapper;

import classfourteen.entity.dto.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Insert("insert into java01_user_info(user_id,user_name,user_pwd,status,created_by,updated_by) values(#{userId},#{userName},#{userPwd},#{status},'sys','sys')")
    int insert(UserDTO userDTO);

    @Select("SELECT * FROM java01_user_info WHERE id = #{id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "userId",  column = "user_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userPwd", column = "user_pwd"),
            @Result(property = "status", column = "status"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "createdBy", column = "created_by"),
            @Result(property = "dateCreated", column = "date_created"),
            @Result(property = "updatedBy", column = "updated_by"),
            @Result(property = "dateUpdated", column = "date_updated")
    })
    UserDTO selectOne(UserDTO userDTO);
}
