package com.ruge.netty.nio.zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author ruge.wu
 * @version 0.0.1
 * @ClassName NewIOServer
 * @date 2020.05.07 16:42
 */
public class NewIOServer {
    public static void main(String[] args) throws Exception {
        InetSocketAddress address = new InetSocketAddress(7001);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(address);
        //创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            int readcount = 0;
            while (-1 != readcount) {
                try {
                    readcount = socketChannel.read(byteBuffer);
                    System.out.println(readcount);
                }catch (Exception ex) {
                    // ex.printStackTrace();
                    break;
                }
                byteBuffer.rewind(); //倒带 position = 0 mark 作废
            }
        }
    }
}