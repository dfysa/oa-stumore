package top.fyl.oastumore.service;

import top.fyl.oastumore.dto.LeaveDTO;

/**
 * @author dfysa
 * @data 27/11/2023 上午8:22
 * @description
 */
public interface ILeaveService {

    /**
     * 提交请假
     *
     * @param dto    DTO
     * @param userId 用户 ID
     */
    void submitLeave(LeaveDTO dto, Long userId);
}