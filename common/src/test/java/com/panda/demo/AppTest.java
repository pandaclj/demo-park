package com.panda.demo;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;
import com.twodfire.util.UuidUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void change() {
        int a = 3, b = 5;
        int c = a ^ b;
        System.out.println(c ^ a);
        System.out.println(c ^ b);
    }

    @Test
    public void subString() {
        String s = "1,2,3,";
        System.out.println(s.substring(0, s.length() - 1));
    }

    @Test
    public void remainder() {
        System.out.println(1 % 100);
    }

    @Test
    public void shortTest() {
        System.out.println((short) 32769);
    }

    @Test
    public void uuidTest() {
        System.out.println(UuidUtil.generate("12345678"));
    }

    @Test
    public void time() {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void waitTest() throws Exception {
        System.out.println("start");
        Object lock = new Object();
        synchronized (lock) {
            lock.wait(1000);
        }
        System.out.println("end");
    }

    @Test
    public void rateLimiter() {
        RateLimiter rateLimiter = RateLimiter.create(1);
        for (int i = 0; i < 10; i++) {
            execute(rateLimiter);
        }
    }

    private void execute(RateLimiter rateLimiter) {
        rateLimiter.acquire();
        System.out.println("获取到锁");
    }

    @Test
    public void testListRemove() {
        List<String> list = Lists.newArrayList("1", "2", "3");
        remove(list, "2");
        System.out.println(JSON.toJSONString(list));
    }

    private void remove(List<String> list, String removeTxt) {
        if (CollectionUtils.isNotEmpty(list)) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                if (it.next().equalsIgnoreCase(removeTxt)) {
                    it.remove();
                }
            }
        }
    }

    @Test
    public void testCollectionUtils() {
        List<String> list1 = Lists.newArrayList("1", "2");
        List<String> list2 = Lists.newArrayList("1", "3");
        List<String> list3 = Lists.newArrayList();
        System.out.println(JSON.toJSONString(CollectionUtils.disjunction(list1, list2)));
        System.out.println(JSON.toJSONString(CollectionUtils.disjunction(list1, list3)));
    }

    @Test
    public void testClassName() {
        Object obj = new HashMap<>();
        System.out.println(obj.getClass().getSimpleName());
    }

    @Test
    public void testParseLong() {
        System.out.println(Long.parseLong("1.0"));
    }

    @Test
    public void testParam() {
        Object[] params = new Object[3];
        Map<String, Object> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", "2");
        params[0] = map;
        params[1] = 1;
        params[2] = "lala";

        System.out.println(JSON.toJSONString(params));
    }

    @Test
    public void printStack() {
        try {
            System.out.println(1 / 0);
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
            e.toString();
        }
    }

    @Test
    public void getName() {
        System.out.println(HashMap.class.getName());
    }

    @Test
    public void testEq() {
        Object obj = new String("你好");
        System.out.println(obj.equals("你好"));
    }

    @Test
    public void emptyList() {
//        Collections.EMPTY_LIST.addAll(Lists.newArrayList(1,2));

//        Collections.EMPTY_LIST.remove(1);

//        Collections.EMPTY_MAP.put("1",1);
        new ArrayList<>(0).addAll(Lists.newArrayList(1, 2));
    }

    @Test
    public void listNll() {
        List<String> list = new ArrayList<>();
        list.add(null);
    }

    @Test
    public void populate() {
        Student student = new Student();
        student.setId("1");
        student.setName("小明");
        Map map = new HashMap();
        map.put("id", "2");
        try {
            BeanUtils.populate(student, map);
            System.out.println(JSON.toJSONString(map));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void describe() {
        Student student = new Student();
        student.setId("1");
        student.setName("小明");
        try {
            Map map = BeanUtils.describe(student);
            System.out.println(JSON.toJSONString(map));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void bigDecimal1() {
        BigDecimal b1 = new BigDecimal(1);
        BigDecimal b2 = new BigDecimal(-1);
        System.out.println(b1.negate());
        System.out.println(b2.negate());

    }

    @Test
    public void subString2() {
        System.out.println("123".substring(2));
    }

    @Test
    public void binaryString() {
        System.out.println(Integer.toBinaryString(2));
    }

    @Test
    public void test_int_array() {
        Integer[] array = new Integer[6];
        System.out.println(array[2]);
    }

    @Test
    public void test_match() {
        List<Long> masks;
        long longval = 1;
        masks = new ArrayList<Long>();
        while (longval > 0) {
            masks.add(longval);
            longval = longval << 1;
        }
        System.out.println(masks);


        long val = 0;
        try {
            val = Long.parseLong("5", 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] arr = toArray(val);
        List<Long> arbitaryList = new ArrayList<Long>(0);
        // List<Long> arbitaryList = arbitaryListThreadLocal.get();
        // arbitaryList.clear();
        for (int i = 0; i < masks.size(); i++) {
            if (i > 0) {
                // sb.append(' ');
            }
            if (val < (masks.get(i) - 1)) {
                break;
            }

            if ((val & masks.get(i)) != 0) {
                // sb.append(String.valueOf(masks.get(i)));

                //只存了为1的值
                arbitaryList.add(masks.get(i));
            }
        }


        String qstr = "0101";
        int length = StringUtils.length(qstr);
        char c;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = length - 1; i >= 0; i--) {
            c = qstr.charAt(length - (i + 1));
            if (c == '1') {
                map.put(i, 1);
            } else if (c == '0') {
                map.put(i, 0);
            }
        }

        System.out.println(map);

    }


    @Test
    public void test_linkedHashMap() {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);

        System.out.println(JSON.toJSONString(map));
    }


    public static byte[] toArray(long val) {

        int off = 0;
        byte[] b = new byte[8];
        b[off + 7] = (byte) (val >>> 0);
        b[off + 6] = (byte) (val >>> 8);
        b[off + 5] = (byte) (val >>> 16);
        b[off + 4] = (byte) (val >>> 24);
        b[off + 3] = (byte) (val >>> 32);
        b[off + 2] = (byte) (val >>> 40);
        b[off + 1] = (byte) (val >>> 48);
        b[off + 0] = (byte) (val >>> 56);
        return b;

    }

    @Test
    public void test_deque() {
        Deque<String> deque = new ArrayDeque<>();
        deque.offerFirst("a");
        deque.offerFirst("b");

        System.out.println(JSON.toJSONString(deque));

        Deque<String> list = new LinkedList<>();
        list.offerFirst("a");
        list.offerFirst("b");

        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void test_String_split() {
        String s = "hello  world";

        String[] array = s.split(" ");


    }

}
