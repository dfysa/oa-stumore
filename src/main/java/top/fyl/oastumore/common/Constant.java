package top.fyl.oastumore.common;

/**
 * @author dfysa
 * @data 26/11/2023 下午6:53
 * @description
 */
public interface Constant {

    /**
     * 总经理级别
     */
    Integer BOSS_LEVEL = 8;
    /**
     * 部门经理级别
     */
    Integer DEPARTMENT_MANAGER_LEVEL = 7;


    /// 请假表单相关
    String LEAVE_FROM_STATE_PROCESS = "process";
    String LEAVE_FROM_STATE_APPROVED = "approved";
    String LEAVE_FROM_STATE_REFUSED = "refused";

    /// 请假流程操作相关
    String LEAVE_PROCESS_ACTION_APPLY = "apply";
    String LEAVE_PROCESS_ACTION_AUDIT = "audit";

    /// 请假流程结果相关
    String LEAVE_PROCESS_RESULT_APPROVED = "approved";
    String LEAVE_PROCESS_RESULT_REFUSED = "refused";

    /// 请假流程状态相关
    String LEAVE_PROCESS_STATE_READY = "ready";
    String LEAVE_PROCESS_STATE_PROCESS = "process";
    String LEAVE_PROCESS_STATE_COMPLETE = "complete";
    String LEAVE_PROCESS_STATE_CANCEL = "cancel";
}