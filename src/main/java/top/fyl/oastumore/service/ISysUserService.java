package top.fyl.oastumore.service;

import top.fyl.oastumore.dto.LoginDTO;
import top.fyl.oastumore.entity.SysUser;
import top.fyl.oastumore.vo.LoginVO;

/**
 * @author dfysa
 * @data 25/11/2023 下午6:25
 * @description
 */
public interface ISysUserService {

    LoginVO login(LoginDTO loginDTO);
}