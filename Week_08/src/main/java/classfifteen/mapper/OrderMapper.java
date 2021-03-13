package classfifteen.mapper;

import classfifteen.entity.dto.OrderDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface OrderMapper {

    @Insert("insert into java01_order_info(order_id,user_id,express_id,order_date,status,created_by,updated_by) values (#{orderId},#{orderId},#{expressId},#{orderDate},#{status},'sys','sys')")
    int insert(OrderDTO orderDTO);

    @Select("SELECT * FROM java01_order_info WHERE order_id = #{orderId} and user_id = #{userId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "orderId",column = "order_id"),
            @Result(property = "userId",  column = "user_id"),
            @Result(property = "expressId", column = "express_id"),
            @Result(property = "orderDate", column = "order_date"),
            @Result(property = "status", column = "status"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "createdBy", column = "created_by"),
            @Result(property = "dateCreated", column = "date_created"),
            @Result(property = "updatedBy", column = "updated_by"),
            @Result(property = "dateUpdated", column = "date_updated")
    })
    OrderDTO selectOne(OrderDTO orderDTO);
}
