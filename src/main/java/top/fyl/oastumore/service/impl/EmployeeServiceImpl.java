package top.fyl.oastumore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.fyl.oastumore.common.Constant;
import top.fyl.oastumore.entity.AdmEmployee;
import top.fyl.oastumore.mapper.AdmEmployeeMapper;
import top.fyl.oastumore.service.IEmployeeService;

/**
 * @author dfysa
 * @data 26/11/2023 下午6:52
 * @description
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Resource
    private AdmEmployeeMapper employeeMapper;
    @Override
    public AdmEmployee getLeaderByEmployeeId(Long employeeId) {
        //三种情况，总经理，部门经理，音通员工
        AdmEmployee employee = employeeMapper.selectById( employeeId);
        LambdaQueryWrapper<AdmEmployee> wrapper = new LambdaQueryWrapper<>();
        if ( Constant.BOSS_LEVEL.equals( employee.getLevel( ))) {
//如果是总经理，直接返回自己
            return employee;
        }else if (Constant.DEPARTMENT_MANAGER_LEVEL.equals(employee.getLevel( ))){
//如果是部门经理，直接返回总经理
            wrapper.eq( AdmEmployee ::getLevel,Constant.BOSS_LEVEL);
            return employeeMapper.selectOne(wrapper);
        }else {
            //如果是普通员工，返回部门经理
            wrapper.eq( AdmEmployee :: getLevel,Constant.DEPARTMENT_MANAGER_LEVEL)
                    .eq( AdmEmployee::getDepartmentId,employee.getDepartmentId());
            return employeeMapper.selectOne(wrapper);
        }}}
