package com.panda.demo.common.util.beancopy;

import com.panda.demo.model.Student;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 借鉴spring的bean copy，可选择忽略null值拷贝和部分属性字段的拷贝
 */
public class BeanCopyUtil {

    /**
     * 对象拷贝，默认不忽略空值
     * source 拷贝到 target
     *
     * @param source
     * @param target
     * @throws Exception
     */
    public static void copyProperties(Object target, Object source) {
        copyProperties(target, source, false);
    }

    /**
     * 对象拷贝，可选择忽略空值
     *
     * @param source
     * @param target
     * @param ignoreNull
     * @throws Exception
     */
    public static void copyProperties(Object target, Object source, boolean ignoreNull) {
        copyProperties(target, source, null, ignoreNull);
    }

    /**
     * 对象拷贝，可选择忽略的属性
     *
     * @param source
     * @param target
     * @param ignoreProperties
     * @throws Exception
     */
    public static void copyProperties(Object target, Object source, String[] ignoreProperties) {
        copyProperties(target, source, ignoreProperties, false);
    }

    /**
     * 对象拷贝，可选择忽略空值和需要忽略的值
     *
     * @param source
     * @param target
     * @param ignoreProperties
     * @param ignoreNull
     * @throws Exception
     */
    public static void copyProperties(Object target, Object source, String[] ignoreProperties, boolean ignoreNull) {
        try {
            Class targetClass = target.getClass();
            List<PropertyDescriptor> targetPds = getPropertyDescriptors(targetClass);
            List ignoreList = ignoreProperties != null ? Arrays.asList(ignoreProperties) : null;

            for (PropertyDescriptor targetPd : targetPds) {
                Method writeMethod = targetPd.getWriteMethod(); //获得set方法
                if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                    PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                    if (sourcePd != null) {
                        Method readMethod = sourcePd.getReadMethod(); //获得get方法
                        //参数类型和返回类型一致
                        if (readMethod != null && writeMethod.getParameterTypes()[0].isAssignableFrom(readMethod.getReturnType())) {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object setParam = readMethod.invoke(source, new Object[0]);
                            if (!(ignoreNull && setParam == null)) {
                                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                    writeMethod.setAccessible(true);
                                }
                                writeMethod.invoke(target, new Object[]{setParam});
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("对象拷贝失败");
        }
    }

    private static List<PropertyDescriptor> getPropertyDescriptors(Class<?> clazz) {
        List<PropertyDescriptor> pds = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            PropertyDescriptor pd = getPropertyDescriptor(clazz, field.getName());
            if (pd != null) {
                pds.add(pd);
            }
        }
        return pds;
    }

    private static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, String propertyName) {
        PropertyDescriptor pd = null;
        try {
            pd = new PropertyDescriptor(propertyName, clazz);
        } catch (IntrospectionException e) {
            //出异常说明没有对应的get、set方法，可忽略
        }
        return pd;
    }

    public static void main(String[] args) {
        Student student1 = new Student();
        student1.setId("1");
        student1.setName("小明");
        student1.setAge(1);


        Student student2 = new Student();
        student2.setName("小红");
//        BeanCopyUtil.copyProperties(student1,student2,true);
        BeanCopyUtil.copyProperties(student1,student2,false);

        System.out.println(student1);
    }
}
