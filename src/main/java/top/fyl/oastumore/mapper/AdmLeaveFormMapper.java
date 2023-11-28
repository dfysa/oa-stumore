package top.fyl.oastumore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.fyl.oastumore.entity.AdmEmployee;
import top.fyl.oastumore.entity.AdmLeaveForm;

import java.util.List;
import java.util.Map;

/**
 * @author dfysa
 * @data 26/11/2023 下午6:48
 * @description
 */
@Mapper
public interface AdmLeaveFormMapper extends BaseMapper<AdmLeaveForm> {

    List<Map<String,Object>> selectByParams(@Param("state")String state,@Param("operatorId") Long operatorId);


}