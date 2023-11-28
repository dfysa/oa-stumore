package top.fyl.oastumore.service;

import top.fyl.oastumore.entity.SysNotice;

import java.util.List;

/**
 * @author dfysa
 * @data 27/11/2023 下午1:54
 * @description
 */
public interface INoticeService {
    void sendNotice(Long receiverId, String content);

    List<SysNotice> noticeList(Long userId);
}
