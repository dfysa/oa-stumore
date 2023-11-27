package top.fyl.oastumore.service;

import top.fyl.oastumore.entity.AdmEmployee;

/**
 * @author dfysa
 * @data 26/11/2023 下午6:52
 * @description
 */
public interface IEmployeeService {

    /**
     * 获取部门领导
     *
     * @param employeeId 员工 ID
     * @return {@link AdmEmployee}
     */
    AdmEmployee getLeaderByEmployeeId(Long employeeId);
}