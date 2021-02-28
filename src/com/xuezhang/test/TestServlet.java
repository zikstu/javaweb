package com.xuezhang.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @description:
 * @author: 学长
 * @date: 2021/2/27 22:47
 */
public class TestServlet {
    public static void main(String[] args) {
        String name = "com.xuezhang.servlet.TestServlet";

        try {
            Class clazz = Class.forName(name);

            Constructor constructor = clazz.getConstructor();

            Object instance = constructor.newInstance();

            System.out.println(instance);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e){
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
