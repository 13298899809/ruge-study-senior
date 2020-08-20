package com.ruge.spark.core.wordcount;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.Arrays;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description:
 * @date 2020/7/30 21:14
 */
public class _01_Java_lambda_HDFS_WordCount {
    public static <JFunction2> void main(String[] args) {
        /*1 获取srark入口 JavaSparkContext*/
        SparkConf sparkConf = new SparkConf();
        /*设置使用本地的spark框架*/
        sparkConf.setMaster("local[*]").setAppName(_01_Java_lambda_HDFS_WordCount.class.getSimpleName());

        /*2 建立与srark的连接*/
        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);

        /*3.1 指定读取目录下的文件*/
        JavaRDD<String> lineRDD = javaSparkContext.textFile("hdfs://47.93.18.115:9000/user/ruge/input/wc.input");

        System.out.println("分词切割前");
        lineRDD.foreach((VoidFunction) o -> System.out.println(o));

        /*分词切割*/
        JavaRDD<String> wordsRDD = lineRDD.flatMap((FlatMapFunction) o -> {
            String line = (String) o;
            return Arrays.asList(line.split("\\s+")).iterator();
        });

        /*遍历读取的文件*/
        System.out.println("分词切割后");
        wordsRDD.foreach((VoidFunction) o -> System.out.println(o));

        /*计数*/
        JavaPairRDD<String, Integer> pairRDD = wordsRDD.mapToPair((PairFunction<String, String, Integer>) s -> new Tuple2<>(s, 1));

        System.out.println("计数后");
        pairRDD.foreach((VoidFunction) o -> System.out.println(o));
        /*聚合 按照相同的key进行数据聚合*/
        JavaPairRDD<String, Integer> resultRDD = pairRDD.reduceByKey((Function2<Integer, Integer, Integer>) Integer::sum);

        System.out.println("聚合后");
        resultRDD.foreach((VoidFunction) o -> System.out.println(o));
    }
}
