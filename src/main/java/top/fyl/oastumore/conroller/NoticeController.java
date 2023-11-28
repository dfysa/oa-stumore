package top.fyl.oastumore.conroller;

import cn.hutool.json.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fyl.oastumore.common.CommonResp;
import top.fyl.oastumore.entity.SysNotice;
import top.fyl.oastumore.service.INoticeService;
import top.fyl.oastumore.util.JwtUtil;

import java.util.List;

/**
 * @author dfysa
 * @data 27/11/2023 下午5:22
 * @description
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Resource
    private INoticeService noticeService;

    @GetMapping("/list")
    public CommonResp<List<SysNotice>> noticeList(@RequestHeader("token")String token){
        JSONObject jsonObject= JwtUtil.getJSONObject(token);
        Long userId =jsonObject.getLong("id");
        return CommonResp.success(noticeService.noticeList(userId));
    }
}
