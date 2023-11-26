package top.fyl.oastumore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.fyl.oastumore.OaApplication;
import top.fyl.oastumore.entity.SysNode;
import top.fyl.oastumore.entity.SysUser;

/**
 * @author dfysa
 * @data 25/11/2023 下午6:21
 * @description
 */
@Mapper
public interface SysNodeMapper extends BaseMapper<SysNode> {
    @SpringBootApplication
    @MapperScan("top.fyl.oastumore.mapper")
    public class OaApplication{
        public static void main(String[] args) {
            SpringApplication.run(OaApplication.class,args);
        }
    }
}