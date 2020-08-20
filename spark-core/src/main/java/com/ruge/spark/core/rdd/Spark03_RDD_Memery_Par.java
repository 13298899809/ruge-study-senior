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
public class Spark03_RDD_Memery_Par implements Serializable {
    public static void main(String[] args) {

        /*1 获取srark入口 JavaSparkContext*/
        SparkConf sparkConf = new SparkConf().setMaster("local[*]").setAppName(Spark03_RDD_Memery_Par.class.getSimpleName());


        /*2 建立与srark的连接*/
        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);

        List<Object> list = Arrays.asList(1, 2, 3, 4, 5);
        /*3.1 指定读取目录下的文件*/
        /*numSlices:指的是并行计算的分片数（其实就是split数据分片、并行度）*/
        JavaRDD<Object> lineRDD = javaSparkContext.parallelize(list, 2);
        System.out.println("分词切割前");
        lineRDD.foreach((VoidFunction) o -> System.out.println(o));

        javaSparkContext.stop();
    }
}
