package top.fyl.oastumore.conroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dfysa
 * @data 25/11/2023 下午5:31
 * @description  @RestController表示这是一个 REST 风格的接口
 * @RequestMapping代表访问这个文件中的所有接口，都需要加上 api 前缀
 * @GetMapping表示这是一个 GET 请求的接口
 */


@RestController
@RequestMapping("/api")
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "Hello Spring Boot";
    }

}
