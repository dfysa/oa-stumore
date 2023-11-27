package top.fyl.oastumore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author dfysa
 * @data 26/11/2023 下午6:42
 * @description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("adm_process_flow")
public class AdmProcessFlow {

    @TableId(value="process_id",type = IdType.AUTO)
    private Long processId;


    private Long formId;


    private Long operatorId;


    private String action;


    private String result;


    private String reason;


    private LocalDateTime auditTime;


    private Integer orderNo;


    private String state;


    private Integer isLast;

    private LocalDateTime createTime;
}
