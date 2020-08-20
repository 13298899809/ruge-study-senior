package com.ruge.spark.core.rdd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description:
 * @date 2020/8/11 21:54
 */
public class Spark05_RDD_simple implements Serializable {
    public static void main(String[] args) {
        /*1 获取srark入口 JavaSparkContext*/
        SparkConf sparkConf = new SparkConf().setMaster("local[*]").setAppName(Spark01_RDD_Memery.class.getSimpleName());


        /*2 建立与srark的连接*/
        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);
        List<Object> list = Arrays.asList(1, 2, 3, 4, 5);
        /*3.1 指定读取目录下的文件*/
        JavaRDD<Object> lineRDD = javaSparkContext.parallelize(list);
        /*从数据集中抽取数据
         * withReplacement : Boolean , True表示进行替换采样，False表示进行非替换采样（抽取的数据是否放回）
         * fraction : Double, 在0~1之间的一个浮点值，表示要采样的记录在全体记录中的比例（重复抽取的几率）
         * seed ：随机种子
         * */
        JavaRDD<Object> dataRdd = lineRDD.sample(true, 0.5);
        dataRdd.collect().forEach(System.out::println);

        javaSparkContext.stop();
    }
}
