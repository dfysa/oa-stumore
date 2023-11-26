package top.fyl.oastumore.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.fyl.oastumore.common.CommonResp;

/**
 * @author dfysa
 * @data 25/11/2023 下午7:13
 * @description
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 所有异常的处理
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResp<?> exceptionHandler(Exception e) {
        log.error("系统异常：", e);
        CommonResp<?> commonResp = new CommonResp<>();
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getMessage());
        return commonResp;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public CommonResp<?> exceptionHandler(BusinessException e) {
        log.error("业务异常：{}", e.getE().getDesc());
        CommonResp<?> commonResp = new CommonResp<>();
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getE().getDesc());
        return commonResp;
    }

}