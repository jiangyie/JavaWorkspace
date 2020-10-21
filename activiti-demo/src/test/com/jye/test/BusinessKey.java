package com.jye.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

/**
 * @author Yangen Jiang
 * @created 2020/10/12 21:22
 */
public class BusinessKey {

    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    //关联businessKey
    @Test
    public void BusinessKeyTest(){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //流程定义key
        String processKey = "myProcess_1";
        //业务标识businessKey
        String businessKey = "10001";

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, businessKey);
        //输出processInstance相关属性
        System.out.println(processInstance.getBusinessKey());
    }

    //全部激活与挂起
    @Test
    public void SuspendOrNot(){
        String processId = "myProcess_1:1:4";
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(processId).singleResult();
        boolean suspended = processDefinition.isSuspended();
        if (suspended){
            //如果暂停则激活，这里将流程定义下的所有流程实例全部激活
            repositoryService.activateProcessDefinitionById(processId, true, null);
            System.out.println("流程定义key" + processId + "被激活");
        }else {
            //如果激活则挂起，这里将流程定义下的所有流程实例全部挂起
            repositoryService.suspendProcessDefinitionById(processId, true, null);
            System.out.println("流程定义key" + processId + "被挂起");
        }

    }

    @Test
    public void suspendOrActiveProcessInstance() {
        // 流程实例id
        String processInstanceId = "10001";
        // 获取RunTimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //根据流程实例id查询流程实例
        ProcessInstance processInstance =
                runtimeService.createProcessInstanceQuery()
                        .processInstanceId(processInstanceId).singleResult();
        boolean suspend = processInstance.isSuspended();
        if(suspend){
            //如果暂停则激活
            runtimeService.activateProcessInstanceById(processInstanceId);
            System.out.println("流程实例： "+processInstanceId+"激活");
        }else{
            //如果激活则挂起
            runtimeService.suspendProcessInstanceById(processInstanceId);
            System.out.println("流程实例： "+processInstanceId+"挂起");
        }
    }
}
