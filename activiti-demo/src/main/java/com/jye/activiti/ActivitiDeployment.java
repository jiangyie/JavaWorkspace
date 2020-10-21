package com.jye.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;

/**
 * @author Yangen Jiang
 * @created 2020/10/11 16:03
 */
public class ActivitiDeployment {

    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine);
        RepositoryService repositoryService = processEngine.getRepositoryService();
    }
}
