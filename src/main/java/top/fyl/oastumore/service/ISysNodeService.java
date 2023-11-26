package top.fyl.oastumore.service;

import top.fyl.oastumore.entity.SysNode;
import top.fyl.oastumore.vo.NodeVO;

import java.util.List;

/**
 * @author dfysa
 * @data 26/11/2023 下午3:31
 * @description
 */
// ISysNodeService
public interface ISysNodeService {

    /**
     * 按角色 ID 查询节点
     *
     * @param roleId 角色 ID
     * @return {@link List}<{@link SysNode}>
     */
    List<NodeVO> selectByRoleId(Integer roleId);
}