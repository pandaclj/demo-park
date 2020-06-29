package example;

import com.panda.demo.Student;
import com.panda.demo.TypeEnum;
import org.junit.Test;
import org.springframework.util.ClassUtils;

import java.util.*;
import java.util.function.Function;

/**
 * Class相关测试案例
 *
 * @author huixiangdou
 * @date 2020/5/29
 */
public class ClassTest {
    /**
     * 测试如何获取ClassLoader
     */
    @Test
    public void test_getClassLoader() {
        System.out.println(Map.class.getClassLoader());

        System.out.println(Map.class.getClass().getClassLoader());
        System.out.println(new HashMap<>().getClass().getClassLoader());

        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(Student.class.getClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());

        System.out.println(ClassUtils.getDefaultClassLoader());
    }

    /**
     * 测试是否是用户自定义的类
     */
    @Test
    public void test_isUserCustomClass() {
        System.out.println(Integer.class.getClassLoader());
        System.out.println(new String[]{}.getClass().getClassLoader());
        System.out.println(Map.class.getClassLoader());
        System.out.println(int.class.getClassLoader());
        System.out.println(Collection.class.getClassLoader());

        System.out.println(Function.class.getClassLoader());
        System.out.println(TypeEnum.class.getClassLoader());

        Runnable r = ()->{};
        System.out.println(r.getClass().getClassLoader());

        System.out.println(Student.class.getClassLoader());


        System.out.println(Student.class.getSimpleName());
        System.out.println(Student.class.getName());
        System.out.println(Student.class.getCanonicalName());

        Student[] ss = new Student[]{};
        System.out.println(ss.getClass().getClassLoader());
        System.out.println(ss.getClass().isArray());

        List<Student> list = new ArrayList<>();
        System.out.println(list.getClass().getClassLoader());



    }
}
