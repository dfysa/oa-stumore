package top.fyl.oastumore.service;

import top.fyl.oastumore.dto.AuditDTO;
import top.fyl.oastumore.dto.LeaveDTO;

import java.util.List;
import java.util.Map;

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

    /**
     * 查询请假列表
     *
     * @param state      状态
     * @param userId     ID
     * @return {@link List}<{@link Map}<{@link String}, {@link Object}>>
     */
    List<Map<String, Object>> selectList(String state, Long userId);

    /**
     * 审核请假
     *
     * @param dto    DTO
     * @param userId 用户 ID
     */
    void auditLeave(AuditDTO dto, Long userId);


}