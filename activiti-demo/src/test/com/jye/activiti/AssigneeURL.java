package com.jye.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yangen Jiang
 * @created 2020/10/13 20:37
 */
public class AssigneeURL {

    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    //部署流程定义
    @Test
    public void test(){
        //获得ProcessEngineConfiguration对象
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        //获得ProcessEngine对象
        ProcessEngine processEngine = configuration.buildProcessEngine();
        System.out.println(processEngine);
        //获得RepositoryService对象
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //部署对象
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/holiday1.bpmn")
                .addClasspathResource("diagram/holiday1.png")
                .deploy();
        System.out.println("id: " + deployment.getId());
        System.out.println("name: " + deployment.getName());
        System.out.println("key: " + deployment.getKey());
    }

    @Test
    public void runtimeServiceAssigneeURL(){
        RuntimeService runtimeService = processEngine.getRuntimeService();

        //设置assigness的取值
        Map<String, Object> map = new HashMap<>();
        map.put("assigness0", "zhangsan");
        map.put("assigness1", "lisi");
        map.put("assigness2", "wangwu");

        runtimeService.startProcessInstanceByKey("holiday1", map);
    }

    //获得businessKey
    @Test
    public void GetBusinessKey(){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        TaskService taskService = processEngine.getTaskService();

        //通过taskService查询个人任务
        Task task = taskService.createTaskQuery().processDefinitionKey("holiday1")
                .taskAssignee("zhangsan").singleResult();

        //通过task获得流程实例id
        String processInstanceId = task.getProcessInstanceId();

        //通过流程实例id,获得流程实例对象
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();

        //通过流程实例对象，获得businessKey
        String businessKey = processInstance.getBusinessKey();
    }
}
