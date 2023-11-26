package top.fyl.oastumore.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import top.fyl.oastumore.entity.SysNode;
import top.fyl.oastumore.entity.SysRoleNode;
import top.fyl.oastumore.mapper.SysNodeMapper;
import top.fyl.oastumore.mapper.SysRoleNodeMapper;
import top.fyl.oastumore.service.ISysNodeService;
import top.fyl.oastumore.vo.NodeVO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dfysa
 * @data 26/11/2023 下午3:32
 * @description
 */
@Service
public class SysNodeServiceImpl implements ISysNodeService {
    @Resource
    private SysNodeMapper sysNodeMapper;
    @Resource
     private SysRoleNodeMapper sysRoleNodeMapper;
    @Override
    public List<NodeVO> selectByRoleId(Integer roleId) {
        // 条件构造
        LambdaQueryWrapper<SysRoleNode> roleNodeWrapper = new LambdaQueryWrapper<>();
        roleNodeWrapper.eq(SysRoleNode::getRoleId, roleId);

        //获取角色关联的全部节点
        List<SysRoleNode> roleNodeList = sysRoleNodeMapper.selectList(roleNodeWrapper);
        // 根据节点ID查询节点
        List<SysNode> sysNodeList = roleNodeList.stream()
                .map(item -> sysNodeMapper.selectById(item.getNodeId()))
                .collect(Collectors.toList());

        // 构造树形结构
        return buildNodeTree(sysNodeList);
    }
    private List<NodeVO> buildNodeTree(List<SysNode> sysNodeList){
        List<NodeVO> tree =new ArrayList<>();
        for (SysNode node : sysNodeList){
            //只有没有父节点的才加入根节点数组
            if (ObjectUtils.isEmpty(node.getParentId())){
                NodeVO nodeVO= BeanUtil.copyProperties(node,NodeVO.class);
                nodeVO.setChildren(getChildNode(nodeVO,sysNodeList));
                tree.add(nodeVO);
            }
        }
        return tree;
    }

    private List<NodeVO> getChildNode(NodeVO nodeVO,List<SysNode> sysNodeList){
        return sysNodeList.stream()
                .filter(sysNode->nodeVO.getNodeId().equals(sysNode.getParentId()))
                .map(sysNode -> BeanUtil.copyProperties(sysNode,NodeVO.class))
                .collect(Collectors.toList());
    }

//        List <SysNode> sysNodeList =new ArrayList<>();
//        roleNodeList.forEach(item->{
//            SysNode sysNode=sysNodeMapper.selectById(item.getNodeId());
//            sysNodeList.add(sysNode);
//        });


    }

