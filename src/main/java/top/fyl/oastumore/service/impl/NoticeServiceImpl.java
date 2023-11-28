package top.fyl.oastumore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.fyl.oastumore.entity.SysNotice;
import top.fyl.oastumore.entity.SysUser;
import top.fyl.oastumore.mapper.SysNoticeMapper;
import top.fyl.oastumore.mapper.SysUserMapper;
import top.fyl.oastumore.service.INoticeService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author dfysa
 * @data 27/11/2023 下午1:55
 * @description
 */
@Service
public class NoticeServiceImpl implements INoticeService {

    @Resource
    private SysNoticeMapper noticeMapper;
    @Resource
    private SysUserMapper userMapper;

    @Override
    public void sendNotice(Long receiverId, String content) {
        noticeMapper.insert(SysNotice.builder()
                .receiverId(receiverId)
                .content(content)
                .createTime(LocalDateTime.now())
                .build());
    }

    @Override
    public List<SysNotice> noticeList(Long userId){
        SysUser sysUser =userMapper.selectById(userId);
        LambdaQueryWrapper<SysNotice> wrapper =new LambdaQueryWrapper<>();
        wrapper.eq(SysNotice:: getReceiverId,sysUser.getEmployeeId())
                .orderByDesc(SysNotice:: getCreateTime);
        return noticeMapper.selectList(wrapper);

    }
}