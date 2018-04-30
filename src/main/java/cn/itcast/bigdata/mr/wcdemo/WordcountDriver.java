package cn.itcast.bigdata.mr.wcdemo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * @author y15079
 * @create 2018-03-21 10:24
 * @desc
 * 相当于一个yarn集群的客户端
 * 需要在此封装我们的mr程序的相关运行参数，指定jar包
 * 最后提交给yarn
 *
 * windows系统下，本地提交模式需要两个jar包才能运行：
 * hadoop-mapreduce-client-common-2.6.0-cdh5.4.1.jar
 * hadoop-mapreduce-client-jobclient-2.6.0-cdh5.4.1.jar
 * 参考资料：
 * http://blog.csdn.net/yew1eb/article/details/45790483
 * https://www.cnblogs.com/chenz/articles/3200111.html
 **/
public class WordcountDriver {
	public static void main(String[] args) throws Exception{
		Configuration conf = new Configuration();

		//本地运行，可以不用配置，默认local
		/*conf.set("mapreduce.framework.name", "local");*/

		//本地模式运行mr程序时，输入输出的数据可以在本地，也可以在hdfs上
		//到底在哪里，就看以下两行配置你用哪行
		/*conf.set("fs.defaultFS", "hdfs://192.168.187.201:9000/");*/
		/*conf.set("fs.defaultFS", "file:///");*/

		//运行集群模式，就是把程序提交到yarn中去运行
		//要想运行为集群模式，以下3个参数要指定为集群上的值
		/*conf.set("mapreduce.framework.name","yarn");
		conf.set("yarn.resourcemanager.hostname","192.168.187.201");
		conf.set("fs.defaultFS", "hdfs://hadoop1:9000/");*/
		Job job = Job.getInstance(conf);

		//使用java -jar命令运行wc.jar包时，需要使用以下命令
		/*job.setJar("/home/hadoop/wc.jar");*/
		//这个是使用hadoop jar运行wc.jar包时，需要使用的命令。指定本程序的jar包所在路径
		job.setJarByClass(WordcountDriver.class);

		//指定本业务job要使用的mapper业务类
		job.setMapperClass(WordcountMapper.class);
		job.setReducerClass(WordcountReducer.class);

		//指定mapper输出数据的kv类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		//指定最终输出的数据的kv类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		//指定需要使用combiner, 以及用哪个类作为combiner的逻辑
		/*job.setCombinerClass(WordcountCombiner.class);*/
		job.setCombinerClass(WordcountReducer.class);

		//如果不这是InputFormat，它默认用的是TextInputformat.class
		/*job.setInputFormatClass(CombineTextInputFormat.class);
		CombineTextInputFormat.setMaxInputSplitSize(job, 4194304);
		CombineTextInputFormat.setMinInputSplitSize(job, 2097152);*/

		//指定job的输入原始文件所在目录
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		//指定job的输出结果所在目录
		FileOutputFormat.setOutputPath(job, new Path (args[1]));

		//将job中配置的相关参数，以及job所用的java类所在的jar包，提交给yarn去运行
		/*job.submit();*/
		boolean res = job.waitForCompletion(true);
		System.exit(res?0:1);
	}
}
