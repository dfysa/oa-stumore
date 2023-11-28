package top.fyl.oastumore.conroller;

import cn.hutool.json.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.fyl.oastumore.common.CommonResp;
import top.fyl.oastumore.dto.LeaveDTO;
import top.fyl.oastumore.service.ILeaveService;
import top.fyl.oastumore.util.JwtUtil;

/**
 * @author dfysa
 * @data 27/11/2023 下午1:45
 * @description
 */
@RestController
@RequestMapping("leave")
public class LeaveController {
    @Resource
    private ILeaveService leaveService;


    @GetMapping("/list")
    public CommonResp<Object> leaveList(@RequestParam(value = "state", required = false, defaultValue = "process") String state,
                                        @RequestHeader("token") String token) {
        JSONObject jsonObject = JwtUtil.getJSONObject(token);
        Long id = jsonObject.getLong("id");
        return CommonResp.success(leaveService.selectList(state, id));
    }
    @PostMapping("/submit")
    public CommonResp<Object> submitLeaveForm(@RequestBody LeaveDTO leaveDTO,
                                              @RequestHeader("token") String token){
        JSONObject jsonObject= JwtUtil.getJSONObject(token);
        Long id= jsonObject.getLong("id");
        return CommonResp.success();

    }



}
