package top.fyl.oastumore.conroller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.fyl.oastumore.common.CommonResp;
import top.fyl.oastumore.dto.LoginDTO;
import top.fyl.oastumore.entity.SysUser;
import top.fyl.oastumore.exception.BusinessException;
import top.fyl.oastumore.exception.BusinessExceptionEnum;
import top.fyl.oastumore.service.ISysNodeService;
import top.fyl.oastumore.service.ISysUserService;
import top.fyl.oastumore.util.JwtUtil;
import top.fyl.oastumore.vo.LoginVO;
import top.fyl.oastumore.vo.NodeVO;

import java.util.List;

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

    @Resource
    private ISysNodeService sysNodeService;

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


        @GetMapping("/node")
    public CommonResp<List<NodeVO>> getNodeList (@RequestHeader("token") String token){
            JSONObject jsonObject = JwtUtil.getJSONObject(token);
            Integer roleId =jsonObject.getInt("roleId");
            return CommonResp.success(sysNodeService.selectByRoleId(roleId));
        }

}