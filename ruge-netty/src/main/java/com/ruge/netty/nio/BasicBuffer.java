package com.ruge.netty.nio;

import java.nio.IntBuffer;

/**
 * @author ruge.wu
 * @version 0.0.1
 * @ClassName BasicBuffer
 * @date 2020.05.06 13:39
 */
public class BasicBuffer {

    public static void main(String[] args) {
        //举例说明Buffer 的使用 (简单说明)
        //创建一个Buffer, 大小为 5, 即可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);
        //向buffer 存放数据
//        intBuffer.put(10);
//        intBuffer.put(11);
//        intBuffer.put(12);
//        intBuffer.put(13);
//        intBuffer.put(14);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }

        //如何从buffer读取数据
        //将buffer转换，读写切换(!!!)
        intBuffer.flip();
        // 从第二个元素开始读
        //intBuffer.position(1);
        System.out.println(intBuffer.get());
        // 只读取三个数据
        //intBuffer.limit(3);
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
