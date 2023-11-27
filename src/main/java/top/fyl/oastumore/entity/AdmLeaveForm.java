package top.fyl.oastumore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author dfysa
 * @data 26/11/2023 下午6:39
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("adm_leave_form")
public class AdmLeaveForm {
    @TableId(value="form_id",type = IdType.AUTO)
    private Long formId;

    private Long employeeId;

    private Integer formType;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String reason;

    private String state;
    private LocalDateTime createTime;


}
