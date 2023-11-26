package top.fyl.oastumore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dfysa
 * @data 25/11/2023 下午5:53
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("sys_role_user")
public class SysRoleUser {

    @TableId(value = "ru_id", type = IdType.AUTO)
    private Long ruId;


    private Long roleId;


    private Integer userId;

}


