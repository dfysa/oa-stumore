package top.fyl.oastumore.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dfysa
 * @data 26/11/2023 下午3:42
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NodeVO {
    private Long nodeId;
    private Integer nodeType ;
    private String nodeName;
    private String url;
    private Integer nodeCode;
    private Long parentId;
    private String icon ;
    private List<NodeVO> children;


}
