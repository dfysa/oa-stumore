package top.fyl.oastumore.service;

/**
 * @author dfysa
 * @data 27/11/2023 下午1:54
 * @description
 */
public interface INoticeService {
    void sendNotice(Long receiverId, String content);
}
