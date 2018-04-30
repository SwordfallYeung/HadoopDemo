package cn.itcast.bigdata.mr.inverindex;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author y15079
 * @create 2018-03-23 13:09
 * @desc
 **/
public class InverIndexStepTwo {
	static class InverIndexStepTwoMapper extends Mapper<LongWritable, Text, Text, Text>{
		@Override
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String line = value.toString();
			String[] files = line.split("--");
			context.write(new Text(files[0]), new Text(files[1]));
		}
	}

	static class InverIndexStepTwoReducer extends Reducer<Text, Text, Text, Text>{
		@Override
		protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			StringBuffer sb = new StringBuffer();
			for (Text text : values){
				sb.append(text.toString().replace("\t", "-->") + "\t");
			}
			context.write(key, new Text(sb.toString()));
		}
	}

	public static void main(String[] args) throws Exception{

		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);

		job.setJarByClass(InverIndexStepTwo.class);

		job.setMapperClass(InverIndexStepTwoMapper.class);
		job.setReducerClass(InverIndexStepTwoReducer.class);

		//指定mapper输出数据的kv类型 , 下面的可以不用写，因为map和reduce的数据类型是一致的
		/*job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);*/

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		FileInputFormat.setInputPaths(job, new Path("D:\\IDEA\\HadoopDemo\\srcdata\\inverindex\\output-step1"));
		FileOutputFormat.setOutputPath(job, new Path("D:\\IDEA\\HadoopDemo\\srcdata\\inverindex\\output-step2"));

		boolean res = job.waitForCompletion(true);
		System.exit(res? 0 : 1);
	}
}
