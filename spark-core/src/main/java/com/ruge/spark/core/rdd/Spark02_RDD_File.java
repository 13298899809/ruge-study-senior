package com.ruge.spark.core.rdd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description: 从内存中加载RDD
 * @date 2020/8/11 21:54
 */
public class Spark02_RDD_File implements Serializable {
    public static void main(String[] args) {

        /*1 获取srark入口 JavaSparkContext*/
        SparkConf sparkConf = new SparkConf().setMaster("local[*]").setAppName(Spark02_RDD_File.class.getSimpleName());


        /*2 建立与srark的连接*/
        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);
        /*3.1 指定读取目录下的文件*/
        //JavaRDD<String> lineRDD = javaSparkContext.textFile("D:\\google\\word.txt");
        JavaRDD<String> lineRDD = javaSparkContext.textFile("D:\\google");


        System.out.println("分词切割前");
        lineRDD.foreach((VoidFunction) o -> System.out.println(o));

        javaSparkContext.stop();
    }
}
