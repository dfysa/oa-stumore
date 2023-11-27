package top.fyl.oastumore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.fyl.oastumore.entity.AdmEmployee;
import top.fyl.oastumore.entity.AdmLeaveForm;

/**
 * @author dfysa
 * @data 26/11/2023 下午6:48
 * @description
 */
@Mapper
public interface AdmLeaveFormMapper extends BaseMapper<AdmLeaveForm> {
    @SpringBootApplication
    @MapperScan("top.fyl.oastumore.mapper")
    public class OaApplication {
        public static void main(String[] args) {
            SpringApplication.run(OaApplication.class, args);
        }
    }

}