package com.panda.demo.common.interview.alibaba;


import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 题目1：一个txt文件中有超过1亿条数据，使用多线程快速请找出这些数据中值最大的10个值，并且打印整体耗时
 */
public class Example1 {
    //可配
    private static final int TOP_NUM = 3;
    //根据内存来算
    private static final int PERMIT_NUM = 3;
    //根据计算的执行线程数
    private static final int LATCH_NUM = 3;
    //每批读取的行数，保证内存不会爆，根据内存来算
    private static int READ_LINE_NUM = 6;
    private static final CountDownLatch latch = new CountDownLatch(LATCH_NUM);
    //小顶堆
    private static PriorityQueue finalResult = new PriorityQueue(TOP_NUM);
    private static Semaphore permit = new Semaphore(PERMIT_NUM);
    private static final String FILE_PATH = "/Users/chenlijiang/tmp/example1.txt";
    private static int THREAD_NUM = Runtime.getRuntime().availableProcessors() * 2;
    private static int THREAD_KEEP_ALIVE_TIME = 60;
    private static int THREAD_QUEUE_NUM = 100;


    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(THREAD_NUM, THREAD_NUM, THREAD_KEEP_ALIVE_TIME, TimeUnit.SECONDS, new LinkedBlockingDeque<>(THREAD_QUEUE_NUM), new ThreadFactory() {
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("topN-" + threadNumber.getAndIncrement() + "-thread-");
            return t;
        }
    });

    //处理找TOP_N的任务
    private static class Task implements Runnable {
        //处理数据
        private List<Integer> datas;
        //放置TOP_N
        private PriorityQueue subResult = new PriorityQueue(TOP_NUM);

        public Task(List<Integer> datas) {
            this.datas = datas;
        }

        @Override
        public void run() {
            System.out.println("处理数据：" + JSON.toJSONString(datas));
            for (Integer data : datas) {
                addData(data, subResult);
            }

            //合并到最终结果中
            merge(subResult);
            datas.clear();
            subResult.clear();
            permit.release();
            latch.countDown();
        }
    }

    //合并到最终结果集
    private synchronized static void merge(PriorityQueue<Integer> subResult) {
        Iterator<Integer> iterator = subResult.iterator();
        while (iterator.hasNext()) {
            Integer subData = iterator.next();
            addData(subData, finalResult);
        }
    }

    //往堆中添加数据
    private static void addData(Integer data, PriorityQueue<Integer> result) {
        if (result.size() < TOP_NUM) {
            result.offer(data);
        } else {
            //获取第一个最小值
            if (data.intValue() > result.peek().intValue()) {
                //需要做替换
                result.poll();
                result.offer(data);
            }
        }
        System.out.println(Thread.currentThread().getName() + "合并结果：" + JSON.toJSONString(result));
    }

    //打印结果
    private static void print() {
        System.out.println("最终结果：" + JSON.toJSONString(finalResult.toArray()));
    }

    public static void execute() {
        try (
                //读取文件
                FileInputStream fis = new FileInputStream(FILE_PATH);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
        ) {
            boolean end = false;

            String line = null;
            while (!end) {
                //获取一个许可
                permit.acquire();
                List<Integer> datas = new ArrayList<>(READ_LINE_NUM);
                //上一次读取的
                if (line != null) {
                    try {
                        datas.add(Integer.valueOf(line));
                    } catch (NumberFormatException e) {
                        //log
                    }
                }
                //文件读完或者读取的行数大于约定的行数，则跳出循环
                while ((line = br.readLine()) != null && datas.size() < READ_LINE_NUM) {
                    try {
                        datas.add(Integer.valueOf(line));
                    } catch (NumberFormatException e) {
                        //log
                    }
                }
                if (line == null) {
                    end = true;
                }

                if (datas.size() > 0) {
                    pool.submit(new Task(datas));
                }
            }

            //文件读取完毕
            latch.await();
            print();

            pool.shutdownNow();
        } catch (Exception e) {
            //log
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        execute();
        System.out.println(new StringBuilder("总耗时：").append(System.currentTimeMillis() - start).append("ms"));
    }
}
