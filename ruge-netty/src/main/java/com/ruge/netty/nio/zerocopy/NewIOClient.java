package com.ruge.netty.nio.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author ruge.wu
 * @version 0.0.1
 * @ClassName NewIOClient
 * @date 2020.05.07 16:42
 */
public class NewIOClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 7001));
        String fileName = "D:\\BaiduNetdiskDownload\\uni-app资料.rar";
        //得到一个文件channel
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
        //准备发送
        long startTime = System.currentTimeMillis();
        //在linux下一个transferTo 方法就可以完成传输
        //在windows 下 一次调用 transferTo 只能发送8m , 就需要分段传输文件, 而且要主要
        //传输时的位置 =》 课后思考...
        //transferTo 底层使用到零拷贝
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("发送的总的字节数 =" + transferCount + " 耗时:" + (System.currentTimeMillis() - startTime));
        //关闭
        fileChannel.close();

    }
}
