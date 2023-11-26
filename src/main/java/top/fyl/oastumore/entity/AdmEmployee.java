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
 * @data 25/11/2023 下午7:47
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("adm_employee")
public class AdmEmployee {
    @TableId(value="employee_id",type = IdType.AUTO)

    private Long employeeId;

    private String name;

    private Long departmentId;

    private String title;

    private Integer level;




}
