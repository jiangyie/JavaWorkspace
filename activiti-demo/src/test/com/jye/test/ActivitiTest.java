package com.jye.test;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Yangen Jiang
 * @created 2020/10/10 15:29
 */
public class ActivitiTest {

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
                .addClasspathResource("diagram/holiday.bpmn")
                .addClasspathResource("diagram/holiday.png")
                .name("请假申请流程")
                .deploy();
        System.out.println("id: " + deployment.getId());
        System.out.println("name: " + deployment.getName());
        System.out.println("name: " + deployment.getKey());
    }

    //查询流程定义
     @Test
     public void searchRepository(){
         RepositoryService repositoryService = processEngine.getRepositoryService();
         List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionCategory().asc()
                 .list();
         if (list != null && list.size() > 0){
             for (ProcessDefinition processDefinition : list) {
                 System.out.println("流程定义ID:"+processDefinition.getId());//流程定义的key+版本+随机生成数
                 System.out.println("流程定义名称:"+processDefinition.getName());//对应HelloWorld.bpmn文件中的name属性值
                 System.out.println("流程定义的key:"+processDefinition.getKey());//对应HelloWorld.bpmn文件中的id属性值
                 System.out.println("流程定义的版本:"+processDefinition.getVersion());//当流程定义的key值相同的情况下，版本升级，默认从1开始
                 System.out.println("资源名称bpmn文件:"+processDefinition.getResourceName());
                 System.out.println("资源名称png文件:"+processDefinition.getDiagramResourceName());
                 System.out.println("部署对象ID:"+processDefinition.getDeploymentId());
                 System.out.println("################################");
             }
         }
     }

     //启动流程实例
    @Test
    public void runtimeServiceTest(){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_1");
        System.out.println("流程定义Id：" + processInstance.getProcessDefinitionId());
        System.out.println("当前活动Id：" + processInstance.getId());
    }

    //查询任务
    @Test
    public void taskServiceTest(){
        String assignee = "张三";
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery().processDefinitionKey("myProcess_1")
                .taskAssignee(assignee)
                .list();
        for (Task task : list) {
            System.out.println("流程实例id：" + task.getProcessInstanceId());
            System.out.println("任务id：" + task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
            System.out.println("任务名称：" + task.getName());
        }

    }

    //完成任务
    @Test
    public void completeTask() {
        //任务id
        String taskId = "10005";
        // 创建TaskService
        TaskService taskService = processEngine.getTaskService();
        //完成任务
        taskService.complete(taskId);
        System.out.println("完成任务id="+taskId);
    }

    //部署删除
    @Test
    public void deleteDeployment() {
        // 流程部署id
        String deploymentId = "1";
        RepositoryService repositoryService = processEngine
                .getRepositoryService();
        repositoryService.deleteDeployment(deploymentId);
    }

    //获得bpmn和png文件
    @Test
    public void getProcessResources() throws IOException {
        // 流程定义id
        String processDefinitionId = "myProcess_1:1:4";
        // 获取repositoryService
        RepositoryService repositoryService = processEngine
                .getRepositoryService();
        // 流程定义对象
        ProcessDefinition processDefinition = repositoryService
                .createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId).singleResult();
        //获取bpmn
        String resource_bpmn = processDefinition.getResourceName();
        //获取png
        String resource_png = processDefinition.getDiagramResourceName();
        // 资源信息
        System.out.println("bpmn： " + resource_bpmn);
        System.out.println("png： " + resource_png);
        File file_png = new File("d:/purchasingflow01.png");
        File file_bpmn = new File("d:/purchasingflow01.bpmn");
        // 输出bpmn
        InputStream resourceAsStream = null;
        resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resource_bpmn);
        FileOutputStream fileOutputStream = new FileOutputStream(file_bpmn);
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            fileOutputStream.write(b, 0, len);
        }
        // 输出图片
        resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resource_png);
        fileOutputStream = new FileOutputStream(file_png);
        // byte[] b = new byte[1024];
        // int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            fileOutputStream.write(b, 0, len);
        }
    }

    //查询历史流程信息
    @Test
    public void HistoryRepositoryTest(){
        HistoryService historyService = processEngine.getHistoryService();
        HistoricActivityInstanceQuery historicActivityInstanceQuery = historyService.createHistoricActivityInstanceQuery();
        List<HistoricActivityInstance> list = historicActivityInstanceQuery.processInstanceId("2501").list();
        for (HistoricActivityInstance historicActivityInstance : list) {
            System.out.println(historicActivityInstance.getActivityId());
            System.out.println(historicActivityInstance.getActivityName());
            System.out.println(historicActivityInstance.getActivityType());
        }
    }
}
