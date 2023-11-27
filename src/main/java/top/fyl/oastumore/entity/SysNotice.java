package top.fyl.oastumore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author dfysa
 * @data 25/11/2023 下午5:53
 * @description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("sys_notice")
public class SysNotice {
    @TableId(value="notice_id",type = IdType.AUTO)
    private Long noticeId;

    private Long receiverId;

    private String content;

    private LocalDateTime createTime;


}
