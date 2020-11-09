package com.jye.activiti;

import org.activiti.engine.RepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Yangen Jiang
 * @created 2020/10/21 22:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:activiti-spring.xml")
public class ActivitiTest {

    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void testReployment(){
        System.out.println("部署对象：" + repositoryService);
    }
}
