package com.ruge.netty.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author ruge.wu
 * @version 0.0.1
 * @ClassName NIOFileChannel01
 * @date 2020.05.06 14:20
 */
public class NIOFileChannel01 {
    /**
     * 1 创建 FileChannel 通道
     * 2 创建 ByteBuffer  缓冲区
     * 3 将数据放置到缓冲区  byteBuffer.put(str.getBytes())
     * 4 缓冲区反转 byteBuffer.flip()
     * 5 将缓冲区的数据写入通道 fileChannel.write(byteBuffer)
     */
    public static void main(String[] args) throws Exception {
        String str = "嘿丷如歌";
        //创建一个输出流->channel
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\google\\file01.txt");
        //通过 fileOutputStream 获取 对应的 FileChannel
        //这个 fileChannel 真实 类型是  FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();
        //创建一个缓冲区 ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //将 str 放入 byteBuffer
        byteBuffer.put(str.getBytes());
        //对byteBuffer 进行flip
        byteBuffer.flip();
        //将byteBuffer 数据写入到 fileChannel
        fileChannel.write(byteBuffer);
        // 关闭 fileOutputStream
        fileOutputStream.close();
    }
}
