package top.fyl.oastumore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.fyl.oastumore.entity.AdmEmployee;

/**
 * @author dfysa
 * @data 25/11/2023 下午7:46
 * @description
 */
@Mapper
public interface AdmEmployeeMapper extends BaseMapper<AdmEmployee> {
    @SpringBootApplication
    @MapperScan("top.fyl.oastumore.mapper")
    public class OaApplication{
        public static void main(String[] args) {
            SpringApplication.run(SysNodeMapper.OaApplication.class,args);
        }
    }
}