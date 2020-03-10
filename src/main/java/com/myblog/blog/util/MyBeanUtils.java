package com.myblog.blog.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

public class MyBeanUtils {
    /*
    获取所有属性值为空的属性名数组
     */
    public static String[] getNullPropertyNames(Object source){
        BeanWrapper beanWrapper =new BeanWrapperImpl(source);
        PropertyDescriptor [] pds =beanWrapper.getPropertyDescriptors();
        List<String> nullPropertyNames=new ArrayList<>();

        for (PropertyDescriptor pd:pds){
            String propertyname=pd.getName();
            if (beanWrapper.getPropertyValue(propertyname)==null){
                nullPropertyNames.add(propertyname);
            }
        }
        return nullPropertyNames.toArray(new String [nullPropertyNames.size()]);
    }

}
