package top.fyl.oastumore.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author dfysa
 * @data 26/11/2023 下午6:51
 * @description
 */
@Data
public class LeaveDTO {
    /**
     * 请假类型（1-事假 2-病假 3-工伤假 4-婚假 5-产假 6-丧假）
     */
    private Integer formType;
    /**
     * 请假起始时间
     */
    private LocalDateTime startTime;
    /**
     * 请假结束时间
     */
    private LocalDateTime endTime;
    /**
     * 请假事由
     */
    private String reason;
}