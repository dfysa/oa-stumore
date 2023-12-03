package top.fyl.oastumore.dto;

import lombok.Data;

/**
 * @author dfysa
 * @data 28/11/2023 下午6:57
 * @description
 */
@Data
public class AuditDTO {
    /**
     * 表单id
     */
    private Long formId;
    /**
     * 结果
     */
    private String result;
    /**
     * 原因
     */
    private String reason;
}