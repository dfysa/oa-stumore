package top.fyl.oastumore.service.impl;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.fyl.oastumore.dto.LoginDTO;
import top.fyl.oastumore.service.ISysUserService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author dfysa
 * @data 25/11/2023 下午6:30
 * @description
 */
@SpringBootTest
class SysUserServiceImplTest {
      @Resource
    private ISysUserService sysUserService;
      @Test
    void login(){
          LoginDTO loginDto =new LoginDTO();
          loginDto.setUsername("admin");
          loginDto.setPassword("123456");
          sysUserService.login(loginDto);
      }
}