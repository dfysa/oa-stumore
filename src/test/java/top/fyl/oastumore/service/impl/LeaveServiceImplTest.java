package top.fyl.oastumore.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.fyl.oastumore.dto.LeaveDTO;
import top.fyl.oastumore.entity.AdmLeaveForm;
import top.fyl.oastumore.service.ILeaveService;
import top.fyl.oastumore.service.ISysNodeService;
import top.fyl.oastumore.vo.NodeVO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author dfysa
 * @data 27/11/2023 下午12:44
 * @description
 */
@SpringBootTest
class LeaveServiceImplTest {
    @Resource
    private ILeaveService leaveService;

    @Test
    void submitLeave() {
        LeaveDTO dto = new LeaveDTO();

        String dateTimeStr = "2023-12-27 11:44:44";
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String dateTime1Str = "2023-12-28 10:44:44";
        LocalDateTime dateTime1 = LocalDateTime.parse(dateTime1Str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        dto.setFormType(1);
        dto.setStartTime(dateTime1);
        dto.setReason("b");
        dto.setEndTime(dateTime);
        Long userId = 1L;
        System.out.println();

         leaveService.submitLeave(dto, userId);

//
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            String json = objectMapper.writeValueAsString(result);
//            System.out.println(json);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
    }
}


