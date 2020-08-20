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
import java.util.Iterator;
import java.util.List;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description:
 * @date 2020/7/30 21:14
 */
public class _01_Java_WordCount {
    public static <JFunction2> void main(String[] args) {
        /*1 获取srark入口 JavaSparkContext*/
        SparkConf sparkConf = new SparkConf();
        /*设置使用本地的spark框架*/
        sparkConf.setMaster("local[*]");
        sparkConf.setAppName(_01_Java_WordCount.class.getSimpleName());

        /*2 建立与srark的连接*/
        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);

        /*3.1 指定读取目录下的文件*/
        JavaRDD<String> lineRDD = javaSparkContext.textFile("D:\\google\\word.txt");

        System.out.println("分词切割前");
        lineRDD.foreach(new VoidFunction() {
            @Override
            public void call(Object o) throws Exception {
                System.out.println(o);
            }
        });

        /*分词切割*/
        JavaRDD<String> wordsRDD = lineRDD.flatMap(new FlatMapFunction() {
            @Override
            public Iterator call(Object o) throws Exception {
                String line = (String) o;
                String[] word = line.split("\\s+");
                List<String> list = Arrays.asList(word);
                return list.iterator();
            }
        });

        /*遍历读取的文件*/
        System.out.println("分词切割后");
        wordsRDD.foreach(new VoidFunction() {
            @Override
            public void call(Object o) throws Exception {
                System.out.println(o);
            }
        });

        /*计数*/
        JavaPairRDD<String, Integer> pairRDD = wordsRDD.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<String, Integer>(s, 1);
            }
        });

        System.out.println("计数后");
        pairRDD.foreach(new VoidFunction() {
            @Override
            public void call(Object o) throws Exception {
                System.out.println(o);
            }
        });
        /*聚合 按照相同的key进行数据聚合*/
        JavaPairRDD<String, Integer> resultRDD = pairRDD.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });

        System.out.println("聚合后");
        resultRDD.foreach(new VoidFunction() {
            @Override
            public void call(Object o) throws Exception {
                System.out.println(o);
            }
        });
        /**/
    }
}
