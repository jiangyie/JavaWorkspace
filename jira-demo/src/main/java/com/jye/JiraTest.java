package com.jye;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

/**
 * @author Yangen Jiang
 * @created 2020/10/25 11:16
 */
public class JiraTest {

    public static void main(String[] args) {
        String project = "";
        String issueType = "";
        String summary = "";
        String description = "";
        String reporter = "";
        String assignee = "";
        String priority = "";
        String environment = "";
        BasicCredentials credentials = new BasicCredentials("jiangyie", "evecom@123");
        JiraClient jiraClient = new JiraClient("http://localhost:8080", credentials);
        try {
        File file = new File("E:\\1.txt");
        Issue newIssue = jiraClient.createIssue(project, issueType)//项目及问题类型
                .field(Field.SUMMARY, summary)//概要
                .field(Field.DESCRIPTION, description)//描述
                .field(Field.REPORTER, reporter)//报告人
                .field(Field.ASSIGNEE, assignee)//经办人
                .field(Field.PRIORITY, priority)//工单等级
                .field("environment", environment)//环境
                .execute();
        newIssue.addAttachment(file);//上传附件
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
