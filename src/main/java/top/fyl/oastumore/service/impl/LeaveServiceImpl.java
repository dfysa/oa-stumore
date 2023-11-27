package top.fyl.oastumore.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.fyl.oastumore.common.Constant;
import top.fyl.oastumore.dto.LeaveDTO;
import top.fyl.oastumore.entity.*;
import top.fyl.oastumore.exception.BusinessException;
import top.fyl.oastumore.exception.BusinessExceptionEnum;
import top.fyl.oastumore.mapper.*;
import top.fyl.oastumore.service.IEmployeeService;
import top.fyl.oastumore.service.ILeaveService;
import top.fyl.oastumore.service.INoticeService;
import top.fyl.oastumore.util.DateUtil;

import java.time.LocalDateTime;
import java.util.Date;

import static top.fyl.oastumore.common.Constant.*;


/**
 * @author dfysa
 * @data 27/11/2023 上午8:22
 * @description
 */
@Slf4j
@Service
public class LeaveServiceImpl implements ILeaveService {

    @Resource
    private AdmLeaveFormMapper leaveFormMapper;
    @Resource
    private AdmProcessFlowMapper processFlowMapper;
    @Resource
    private AdmEmployeeMapper employeeMapper;
    @Resource
    private SysUserMapper userMapper;
    @Resource
    INoticeService noticeService;
    @Resource
    private AdmDepartmentMapper departmentMapper;
    @Resource
    private IEmployeeService employeeService;

    @Override
    @Options(useGeneratedKeys = true,keyProperty = "form_id" )
    @Transactional( rollbackFor = Exception.class)
    public void submitLeave(LeaveDTO dto,Long userId) {

        //属性转换，将DTO转换为LeaveForm
        AdmLeaveForm leaveForm = BeanUtil.copyProperties(dto,AdmLeaveForm.class);
        SysUser sysUser = userMapper.selectById(userId);
        //获得员工信息
        AdmEmployee employee = employeeMapper.selectById(sysUser.getEmployeeId());

        //有未审批的请假表单，不能提交
        LambdaQueryWrapper<AdmLeaveForm> queryWrapper = new LambdaQueryWrapper<>( );
        queryWrapper.eq(AdmLeaveForm:: getEmployeeId,employee.getEmployeeId())
                    .eq(AdmLeaveForm::getState,Constant.LEAVE_FROM_STATE_PROCESS);
        if ( leaveFormMapper.selectCount( queryWrapper) > 0){
            throw new BusinessException(BusinessExceptionEnum.LEAVE_PROCESSING);}
        //设置请假表单状态
        if ( employee.getLevel() == 8){
            //如果是总经理，设置为市批通过
            leaveForm.setState( Constant.LEAVE_FROM_STATE_APPROVED );
        }else {
            //如果是普通员工，设置为审批中
            leaveForm.setState( Constant.LEAVE_FROM_STATE_PROCESS );
        }
        //插入请假表单
        leaveForm.setEmployeeId( employee.getEmployeeId());
        leaveForm.setCreateTime(LocalDateTime.now());
        leaveFormMapper.insert(leaveForm);
        log.info("请假表单:{}", leaveForm );

        //TODO 发送通知

        // 获取员工部门
        AdmDepartment department=departmentMapper.selectById(employee.getDepartmentId());

        // 获取上级领导
        AdmEmployee leader=employeeService.getLeaderByEmployeeId(employee.getEmployeeId());


        // 获取领导部门
        AdmDepartment leaderDepartment=departmentMapper.selectById(leader.getDepartmentId());
        // 发送通知
        if (!BOSS_LEVEL.equals(employee.getLevel())){



        // 发送给领导
            String toLeader =String.format("%s-%s提起请假申请[%s-%s] ,请您审批",
                    department.getDepartmentName(),
                    employee.getName(),
                    DateUtil.dateFormat(leaveForm.getStartTime()),
                    DateUtil.dateFormat(leaveForm.getEndTime()));
            noticeService.sendNotice(leader.getEmployeeId(), toLeader);

         // 发送给自己
            String toMe=String.format("您的请假申请 [%s-%s],已移交给%s-s%审批",
                    DateUtil.dateFormat(leaveForm.getStartTime()),
                    DateUtil.dateFormat(leaveForm.getEndTime()),
                    leaderDepartment.getDepartmentName(),
                    leader.getName());
            noticeService.sendNotice(employee.getEmployeeId(), toMe);

        }
        // 构造流程

        ///构造流程
        // 1. 先发送一条给自己的流程
        AdmProcessFlow applyForm=AdmProcessFlow.builder()
                .formId(leaveForm.getFormId())
                .operatorId(leaveForm.getEmployeeId())
                .action(Constant.LEAVE_PROCESS_ACTION_APPLY)
                .createTime(LocalDateTime.now())
                .orderNo(1)
                .state(Constant.LEAVE_PROCESS_STATE_COMPLETE)
                .isLast(0)
                .build();
        processFlowMapper.insert(applyForm);
        // 2. 发送一条给上级领导的流程
        // 获取上级领导
//        AdmEmployee leader =employeeService.getLeaderByEmployeeId(employee.getEmployeeId());
        //判断请假时长是否超过72h
        boolean checkHours= DateUtil.checkHours(leaveForm.getStartTime(),leaveForm.getEndTime());
        //根据级别处理业务
        switch(employee.getLevel()){
            case 1,2,3,4,5,6->{
                // 普通员工请假
                AdmProcessFlow managerFlow=AdmProcessFlow.builder()
                        .formId(leaveForm.getFormId())
                        .operatorId(leaveForm.getEmployeeId())
                        .action(LEAVE_PROCESS_ACTION_AUDIT)
                        .createTime(LocalDateTime.now())
                        .orderNo(2)
                        .state(LEAVE_PROCESS_STATE_PROCESS)
                        .build();
                if(checkHours){
                    //大于72小时
                    managerFlow.setIsLast(0);
                    processFlowMapper.insert(managerFlow);
                    //上报总经理
                    AdmProcessFlow bossFlow=AdmProcessFlow.builder()
                            .formId(leaveForm.getFormId())
                            // 获得部门经理的上级领导，也就是总经理
                            .operatorId(employeeService.getLeaderByEmployeeId(leader.getEmployeeId()).getEmployeeId())
                            .action(LEAVE_PROCESS_ACTION_AUDIT)
                            .createTime(LocalDateTime.now())
                            .orderNo(3)
                            .state(LEAVE_PROCESS_STATE_READY)
                            .isLast(1)
                            .build();
                    processFlowMapper.insert(managerFlow);
                }else{
                    // 小于72小时， 不需要上报总经理。试着设置最后一条流程
                    managerFlow.setIsLast(1);
                    processFlowMapper.insert(managerFlow);

                }

            }


            case 7->{
                //部门经理，上报总经理
                AdmProcessFlow bossFlow=AdmProcessFlow.builder()
                        .formId(leaveForm.getFormId())
                        .operatorId(employeeService.getLeaderByEmployeeId(leader.getEmployeeId()).getEmployeeId())
                        .action(LEAVE_PROCESS_ACTION_AUDIT)
                        .createTime(LocalDateTime.now())
                        .orderNo(2)
                        .state(LEAVE_PROCESS_STATE_READY)
                        .isLast(1)
                        .build();
                processFlowMapper.insert(bossFlow);

            }
            case 8->{
                //部门经理，上报总经理
                AdmProcessFlow bossFlow=AdmProcessFlow.builder()
                        .formId(leaveForm.getFormId())
                        .operatorId(leaveForm.getEmployeeId())
                        .action(LEAVE_PROCESS_ACTION_AUDIT)
                        .createTime(LocalDateTime.now())
                        .result(LEAVE_PROCESS_RESULT_APPROVED)
                        .reason("自动通过")
                        .auditTime(LocalDateTime.now())
                        .orderNo(2)
                        .state(LEAVE_PROCESS_STATE_COMPLETE)
                        .isLast(1)
                        .build();
                processFlowMapper.insert(bossFlow);

            }
            default -> throw new BusinessException(BusinessExceptionEnum.EMPLOYEE_LEVEL_ERROR);



        }


    }



    }
