package com.jye.activiti;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @author Yangen Jiang
 * @created 2020/10/13 21:19
 */
public class MyTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        //指定负责人
        delegateTask.setAssignee("zhangsan");
    }
}
