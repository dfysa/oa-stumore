package top.fyl.oastumore.conroller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fyl.oastumore.common.CommonResp;
import top.fyl.oastumore.dto.LoginDTO;
import top.fyl.oastumore.entity.SysUser;
import top.fyl.oastumore.exception.BusinessException;
import top.fyl.oastumore.exception.BusinessExceptionEnum;
import top.fyl.oastumore.service.ISysUserService;
import top.fyl.oastumore.vo.LoginVO;

/**
 * @author dfysa
 * @data 25/11/2023 下午6:26
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private ISysUserService sysUserService;

    @PostMapping("/login")
    public CommonResp<LoginVO> login(@RequestBody LoginDTO loginDTO) {
        if (ObjectUtils.isEmpty(loginDTO)
                || StrUtil.isBlank(loginDTO.getUsername())
                || StrUtil.isBlank(loginDTO.getPassword())){
            throw new BusinessException(BusinessExceptionEnum.REQUEST_PARAMS_IS_NOT_EMPTY);

        }
        LoginVO loginVo = sysUserService.login(loginDTO);
        return CommonResp.success(loginVo);

}
}