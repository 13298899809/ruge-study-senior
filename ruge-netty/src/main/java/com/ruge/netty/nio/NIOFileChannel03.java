package com.ruge.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author ruge.wu
 * @version 0.0.1
 * @ClassName NIOFileChannel03
 * @date 2020.05.06 14:47
 */
public class NIOFileChannel03 {
    /**
     * 同一个缓冲池 同时完成读和写
     */
    public static void main(String[] args) throws Exception {

        FileInputStream fileInputStream = new FileInputStream("D:\\google\\file01.txt");
        FileChannel fileChannel01 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\google\\file02.txt");
        FileChannel fileChannel02 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true) {
            //清空buffer
            byteBuffer.clear();
            int read = fileChannel01.read(byteBuffer);
            System.out.println("read =" + read);
            //表示读完
            if (read == -1) {
                break;
            }
            //将buffer 中的数据写入到 fileChannel02 -- 2.txt
            byteBuffer.flip();
            fileChannel02.write(byteBuffer);
        }

        //关闭相关的流
        fileInputStream.close();
        fileOutputStream.close();
    }
}
