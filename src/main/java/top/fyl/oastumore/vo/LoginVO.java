package top.fyl.oastumore.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.fyl.oastumore.entity.SysUser;

/**
 * @author dfysa
 * @data 25/11/2023 下午7:23
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {
    private Long userId;
    private String name;
    private String departmentName;
    private String avatar;
    private String title;
    private int level;
    private String role;
    private String token;
}