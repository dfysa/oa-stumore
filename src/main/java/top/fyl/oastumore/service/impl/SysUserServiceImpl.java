package top.fyl.oastumore.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import top.fyl.oastumore.dto.LoginDTO;
import top.fyl.oastumore.entity.AdmEmployee;
import top.fyl.oastumore.entity.SysRoleUser;
import top.fyl.oastumore.entity.SysUser;
import top.fyl.oastumore.exception.BusinessException;
import top.fyl.oastumore.exception.BusinessExceptionEnum;
import top.fyl.oastumore.mapper.*;
import top.fyl.oastumore.service.ISysUserService;
import top.fyl.oastumore.util.JwtUtil;
import top.fyl.oastumore.vo.LoginVO;

import static top.fyl.oastumore.util.Md5Util.md5Digest;

/**
 * @author dfysa
 * @data 25/11/2023 下午6:26
 * @description
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysRoleUserMapper sysRoleUserMapper;
    @Resource
    private AdmEmployeeMapper employeeMapper;
    @Resource
    private AdmDepartmentMapper departmentMapper;
    @Resource
    private SysRoleMapper roleMapper;


    @Override
    public LoginVO login(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        SysUser sysUser = sysUserMapper.selectOne(wrapper);
        // 判断用户是否存在
        if (ObjectUtils.isEmpty(sysUser)) {
            throw new BusinessException(BusinessExceptionEnum.USER_IS_NOT_EXIST);
        }
        // 判断密码是否正确
        String password = loginDTO.getPassword();
        // md5加盐加密
        String md5Password = md5Digest(password, sysUser.getSalt());
        if (!md5Password.equals(sysUser.getPassword())) {
            throw new BusinessException(BusinessExceptionEnum.PASSWORD_IS_WRONG);
        }
        // 获取员工信息
        AdmEmployee employee = employeeMapper.selectById(sysUser.getEmployeeId());
        // 获取部门信息
        String departmentName = departmentMapper.selectById(employee.getDepartmentId()).getDepartmentName();
        // 获取角色信息
        LambdaQueryWrapper<SysRoleUser> roleUserWrapper = new LambdaQueryWrapper<>();
        roleUserWrapper.eq(SysRoleUser::getUserId, sysUser.getUserId());
        // 因为一个用户只有一个角色，所以直接 selectOne
        SysRoleUser roleUser = sysRoleUserMapper.selectOne(roleUserWrapper);
        String role = roleMapper.selectById(roleUser.getRoleId()).getRoleDescription();
        // 生成token
        String token = JwtUtil.createToken(sysUser.getUserId(), roleUser.getRoleId());
        // 同名的属性拷贝
        LoginVO loginVO = BeanUtil.copyProperties(employee, LoginVO.class);
        loginVO.setUserId(sysUser.getUserId());
        loginVO.setToken(token);
        loginVO.setDepartmentName(departmentName);
        loginVO.setRole(role);
        return loginVO;
    }
}