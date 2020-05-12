package com.ruge.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author ruge.wu
 * @version 0.0.1
 * @ClassName ScatteringAndGatheringTest
 * @date 2020.05.06 16:17
 */
public class ScatteringAndGatheringTest {
    /**
     * Scattering：将数据写入到buffer时，可以采用buffer数组，依次写入  [分散]
     * Gathering: 从buffer读取数据时，可以采用buffer数组，依次读
     */
    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

        //绑定端口到socket ，并启动
        serverSocketChannel.socket().bind(inetSocketAddress);

        //创建buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //等客户端连接(telnet)
        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 8;   //假定从客户端接收8个字节
        //循环的读取
        while (true) {

            int byteRead = 0;
            while (byteRead < messageLength ) {
                long l = socketChannel.read(byteBuffers);
                byteRead += l; //累计读取的字节数
                System.out.println("byteRead=" + byteRead);
                //使用流打印, 看看当前的这个buffer的position 和 limit
                Arrays.asList(byteBuffers).stream()
                        .map(buffer -> "postion=" + buffer.position() + ", limit=" + buffer.limit())
                        .forEach(System.out::println);
            }

            //将所有的buffer进行flip
            Arrays.asList(byteBuffers)
                    .forEach(buffer -> buffer.flip());

            //将数据读出显示到客户端
            long byteWirte = 0;
            while (byteWirte < messageLength) {
                long l = socketChannel.write(byteBuffers);
                byteWirte += l;
            }

            //将所有的buffer 进行clear
            Arrays.asList(byteBuffers).forEach(buffer-> {
                buffer.clear();
            });

            System.out.println("byteRead:=" + byteRead + " byteWrite=" + byteWirte + ", messagelength" + messageLength);
        }
    }
}
