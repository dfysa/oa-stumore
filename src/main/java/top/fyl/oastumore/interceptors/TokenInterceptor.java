package top.fyl.oastumore.interceptors;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.fyl.oastumore.exception.BusinessException;
import top.fyl.oastumore.exception.BusinessExceptionEnum;
import top.fyl.oastumore.util.JwtUtil;

/**
 * @author dfysa
 * @data 26/11/2023 下午4:18
 * @description
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 从请求头中获取token
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            throw new BusinessException(BusinessExceptionEnum.TOKEN_IS_EMPTY);

        }
        //验证token
        boolean validate = JwtUtil.validate(token);

        //如果验证失败，抛出异常
        if (!validate) {
            throw new BusinessException(BusinessExceptionEnum.TOKEN_IS_ERROR);

        }
        return true;
    }
}