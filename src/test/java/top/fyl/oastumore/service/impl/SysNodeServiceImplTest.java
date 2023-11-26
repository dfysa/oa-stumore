package top.fyl.oastumore.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.fyl.oastumore.service.ISysNodeService;
import top.fyl.oastumore.vo.NodeVO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author dfysa
 * @data 26/11/2023 下午3:40
 * @description
 */
@SpringBootTest
class SysNodeServiceImplTest {

    @Resource
    private ISysNodeService sysNodeService;


    @Test
    void selectByRoleId() {
        List<NodeVO> result = sysNodeService.selectByRoleId(1);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(result);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}