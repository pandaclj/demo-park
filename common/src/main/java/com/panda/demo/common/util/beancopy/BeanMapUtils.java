package com.panda.demo.common.util.beancopy;

import com.google.common.collect.Lists;
import com.panda.demo.model.Student;
import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;

/**
 * @author huixiangdou
 * @date 2018/10/30
 */
public class BeanMapUtils {
    private static DozerBeanMapper mapper = new DozerBeanMapper();

    static {
        mapper.setMappingFiles(Lists.newArrayList("dozer.xml"));
    }

    public static void map(Object source, Object destination, String mapId) throws MappingException {
        mapper.map(source, destination, mapId);
    }

    /**
     * {@inheritDoc}
     */
    public static <T> T map(Object source, Class<T> destinationClass, String mapId) throws MappingException {
        return mapper.map(source, destinationClass, mapId);
    }

    /**
     * {@inheritDoc}
     */
    public static <T> T map(Object source, Class<T> destinationClass) throws MappingException {
        return mapper.map(source, destinationClass);
    }

    public static void map(Object source, Object destination) throws MappingException {
        mapper.map(source, destination);
    }

    public static void main(String[] args) {
        Student student1 = new Student();
        student1.setId("1");
        student1.setName("小明");
        student1.setAge(1);


        Student student2 = new Student();
        student2.setName("小红");
        BeanMapUtils.map(student1,student2);

        System.out.println(student1);
    }
}
