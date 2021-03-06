package com.ruge.spark.core.examples.RDD_API.WordCount

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 问题1 增加依赖
 * <dependency>
 * <groupId>org.slf4j</groupId>
 * <artifactId>slf4j-simple</artifactId>
 * <version>1.7.7</version>
 * </dependency>
 *
 *
 * 问题2 -Xms256m -Xmx1024m
 *
 * 问题3 hadoop环境  https://github.com/srccodes/hadoop-common-2.2.0-bin
 *
 * 问题4 guava版本的问题
 */
object _01_Spark_WorkCount {

  def main(args: Array[String]): Unit = {
    /*1 准备spark环境*/
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName(_01_Spark_WorkCount.getClass.getSimpleName)

    /*2 建立与srark的连接*/
    val sc: SparkContext = new SparkContext(sparkConf)

    /*3.1 指定读取目录下的文件*/
    val fileRDD: RDD[String] = sc.textFile("D:\\google\\word.txt")

    /*3.2 将文件中的数据进行分词*/
    val wordRDD: RDD[String] = fileRDD.flatMap(x => x.split("\\s+"))

    /*3.3 转换数据结构 word => (word, 1)*/
    val word2OneRDD: RDD[(String, Int)] = wordRDD.map(word => (word, 1))

    /*3.4 将转换结构后的数据按照相同的单词进行分组聚合*/
    val word2CountRDD: RDD[(String, Int)] = word2OneRDD.reduceByKey((v1, v2) => (v1 + v2))

    /*3.5 将数据聚合结果采集到内存中*/
    val word2Count: Array[(String, Int)] = word2CountRDD.collect()

    // 打印结果
    word2Count.foreach(println)

    /*4 关闭Spark连接*/
    sc.stop()


  }

}
