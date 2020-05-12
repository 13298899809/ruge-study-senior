package com.ruge.netty.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author ruge.wu
 * @version 0.0.1
 * @ClassName MappedByteBufferTest
 * @date 2020.05.06 16:04
 */
public class MappedByteBufferTest {
    /**
     * MappedByteBuffer 可让文件直接在内存(堆外内存)修改, 操作系统不需要拷贝一次
     */
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\google\\file01.txt", "rw");
        //获取对应的通道
        FileChannel channel = randomAccessFile.getChannel();
        /**
         * 参数1: FileChannel.MapMode.READ_WRITE 使用的读写模式
         * 参数2: 0 可以直接修改的起始位置
         * 参数3: 6 是映射到内存的大小(不是索引位置) ,即将 1.txt 的多少个字节映射到内存
         * 可以直接修改的范围就是 0-6
         * 实际类型 DirectByteBuffer
         */
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 6);
        mappedByteBuffer.put(0, (byte) 'A');
        mappedByteBuffer.put(1, (byte) 'B');
        mappedByteBuffer.put(2, (byte) 'C');//IndexOutOfBoundsException
        mappedByteBuffer.put(3, (byte) 'D');//IndexOutOfBoundsException
        mappedByteBuffer.put(4, (byte) 'E');//IndexOutOfBoundsException
        mappedByteBuffer.put(5, (byte) 'F');//IndexOutOfBoundsException
        randomAccessFile.close();
        System.out.println("修改成功~~");
    }
}
