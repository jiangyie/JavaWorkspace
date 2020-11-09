package com.jye.springbootactiviti;

import com.jye.springbootactiviti.utils.SecurityUtil;
import net.bytebuddy.asm.Advice;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootActivitiApplicationTests {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private SecurityUtil securityUtil;

    /**
     * 查看流程定义
     * @param []
     * @return void
     * @author Yangen Jiang
     * @created 2020-10-22 09:18:54
     */
    @Test
    void contextLoads() {
        securityUtil.logInAs("myProcess_1");
        processRuntime.processDefinitions(Pageable.of(0, 10));
    }

}
