package com.ruge.netty.nio;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author ruge.wu
 * @version 0.0.1
 * @ClassName NIOFileChannel02
 * @date 2020.05.06 14:37
 */
public class NIOFileChannel02 {
    /**
     * 1 创建 FileChannel 通道
     * 2 创建 ByteBuffer 缓冲区 ByteBuffer.allocate((int) file.length())
     * 3 将通道的数据 读入缓冲区 fileChannel.read(byteBuffer)
     */
    public static void main(String[] args) throws Exception {
        //创建文件的输入流
        File file = new File("D:\\google\\file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        //通过fileInputStream 获取对应的FileChannel -> 实际类型  FileChannelImpl
        FileChannel fileChannel = fileInputStream.getChannel();
        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        //将 通道的数据读入到Buffer
        fileChannel.read(byteBuffer);
        //将byteBuffer 的 字节数据 转成String
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }
}
