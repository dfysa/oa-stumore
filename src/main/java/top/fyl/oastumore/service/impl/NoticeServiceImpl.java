package top.fyl.oastumore.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.fyl.oastumore.entity.SysNotice;
import top.fyl.oastumore.mapper.SysNoticeMapper;
import top.fyl.oastumore.service.INoticeService;

import java.time.LocalDateTime;

/**
 * @author dfysa
 * @data 27/11/2023 下午1:55
 * @description
 */
@Service
public class NoticeServiceImpl implements INoticeService {

    @Resource
    private SysNoticeMapper noticeMapper;

    @Override
    public void sendNotice(Long receiverId, String content) {
        noticeMapper.insert(SysNotice.builder()
                .receiverId(receiverId)
                .content(content)
                .createTime(LocalDateTime.now())
                .build());
    }
}