package com.common.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Set;

public class ProUtil {
    public static boolean isEmpty(Object o){
        if (o==null){
            return true;
        }
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(o.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if (propertyDescriptors==null||propertyDescriptors.length==0){
                return true;
            }
            for (PropertyDescriptor propertyDescriptor:propertyDescriptors){
                String name = propertyDescriptor.getName();
                if (name.equals("class")){
                    continue;
                }
                Method readMethod = propertyDescriptor.getReadMethod();
                Object invoke = readMethod.invoke(o);
                if (invoke==null){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Object o, Set<String> set){
        if (o==null){
            return true;
        }
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(o.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if (propertyDescriptors==null||propertyDescriptors.length==0){
                return true;
            }
            for (PropertyDescriptor propertyDescriptor:propertyDescriptors){
                String name = propertyDescriptor.getName();
                if (name.equals("class")){
                    continue;
                }
                if (set.contains(name)){
                    continue;
                }
                Method readMethod = propertyDescriptor.getReadMethod();
                Object invoke = readMethod.invoke(o);
                if (invoke==null){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }
}
