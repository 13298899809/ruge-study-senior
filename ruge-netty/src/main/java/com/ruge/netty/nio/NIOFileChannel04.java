package com.ruge.netty.nio;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.nio.channels.FileChannel;

/**
 * @author ruge.wu
 * @version 0.0.1
 * @ClassName NIOFileChannel04
 * @date 2020.05.06 15:01
 */
public class NIOFileChannel04 {
    /**
     * FileChannel 实现 文件复制
     */
    public static void main(String[] args) throws Exception {
        //初始化数据
        String imgUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588759678060&di=5912a6b3698341f5ea49a0af8ca19449&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F42a98226cffc1e174eec8bf14190f603728de99b.jpg";
        File file = new File("D:\\google\\file03.jpg");
        FileUtils.copyInputStreamToFile(new URL(imgUrl).openStream(), file);

        //创建相关流
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\google\\file04.jpg");

        //获取各个流对应的filechannel
        FileChannel sourceCh = fileInputStream.getChannel();
        FileChannel destCh = fileOutputStream.getChannel();

        //使用transferForm完成拷贝
        destCh.transferFrom(sourceCh, 0, sourceCh.size());
        //关闭相关通道和流
        sourceCh.close();
        destCh.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
