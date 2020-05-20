package com.panda.demo.common.example;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 如果写一个byte，读一个byte，再读一个byte就要等待
 * 如果写为空，去读，则读需要等待
 *
 *
 * 概括：必须要有数据才可以读，否则就等待
 *写的话，如果写指针等于读指针则需要等待，则说明写满了，需要等待读去消费数据
 *
 * @author huixiangdou
 * @date 2020/4/30
 */
public class PipedTest {
    public static void main(String[] args) throws Exception {
        PipedInputStream is = new PipedInputStream();
        PipedOutputStream os = new PipedOutputStream();
        os.connect(is);

        os.write(1);
        is.read();
    }
}
