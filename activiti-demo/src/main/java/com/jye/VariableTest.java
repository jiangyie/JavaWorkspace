package com.jye;

import com.jye.model.Holiday;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yangen Jiang
 * @created 2020/10/19 21:29
 */
public class VariableTest {

    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //部署
        /*RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("diagram/holiday3.bpmn")
                .addClasspathResource("diagram/holiday3.png")
                .name("请假流程")
                .deploy();
        System.out.println(deploy.getId());
        System.out.println(deploy.getName());*/

        //启动流程实例
        /*RuntimeService runtimeService = processEngine.getRuntimeService();
        String key = "myProcess_1";
        Map<String, Object> map = new HashMap<>();
        Holiday holiday = new Holiday();
        holiday.setNum(1F);
        map.put("holiday", holiday);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, map);
        System.out.println(processInstance.getName());
        System.out.println(processInstance.getProcessDefinitionId());*/

        //完成任务
        TaskService taskService = processEngine.getTaskService();
        String key = "myProcess_1";
        Task task = taskService.createTaskQuery().processDefinitionKey(key)
                .taskAssignee("lisi").singleResult();
        if (task != null){
            taskService.complete(task.getId());
        }

    }
}
