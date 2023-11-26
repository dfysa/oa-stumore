package top.fyl.oastumore.exception;

import lombok.Getter;
import lombok.ToString;

/**
 * @author dfysa
 * @data 25/11/2023 下午7:13
 * @description
 */
@Getter
@ToString
public enum BusinessExceptionEnum {

    REQUEST_PARAMS_IS_NOT_EMPTY("请求参数不能为空"),
    USER_IS_NOT_EXIST("用户不存在"),
    PASSWORD_IS_WRONG("密码错误");

    private final String desc;

    BusinessExceptionEnum(String desc) {
        this.desc = desc;
    }
}