package com.jye.java;

import org.junit.Test;

import java.util.UUID;

/**
 * @author Yangen Jiang
 * @created 2020/10/14 23:22
 */
public class HelloWorld {

    @Test
    public void UUIDCode(){
        int i = UUID.randomUUID().hashCode();
        System.out.println(i);
    }
}
