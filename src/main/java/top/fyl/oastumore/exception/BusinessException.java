package top.fyl.oastumore.exception;

import lombok.Getter;

/**
 * @author dfysa
 * @data 25/11/2023 下午7:13
 * @description
 */
@Getter
public class BusinessException extends RuntimeException{

    private BusinessExceptionEnum e;

    public BusinessException(BusinessExceptionEnum e) {
        this.e = e;
    }

    public void setE(BusinessExceptionEnum e) {
        this.e = e;
    }

    /**
     * 不写入堆栈信息，提高性能
     *
     * @return {@link Throwable}
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}