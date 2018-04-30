package cn.itcast.bigdata.mr.inverindex;

import cn.itcast.bigdata.mr.mapsidejoin.MapSideJoin;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;

/**
 * @author y15079
 * @create 2018-03-23 13:09
 * @desc
 **/
public class InverIndexStepOne {
	static class InverIndexStepOneMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
		Text k = new Text();
		IntWritable v = new IntWritable(1);

		@Override
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String line = value.toString();

			String[] words = line.split(" ");

			FileSplit inputSplit = (FileSplit) context.getInputSplit();
			String fileName = inputSplit.getPath().getName();
			for (String word: words) {
				k.set(word + "--" + fileName);
				context.write(k, v);
			}
		}
	}

	static class InverIndexStopOneReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
		@Override
		protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			int count = 0;
			for (IntWritable value : values){
				count += value.get();
			}
			context.write(key, new IntWritable(count));
		}
	}

	public static void main(String[] args) throws Exception{
		Configuration conf = new Configuration();

		Job job = Job.getInstance(conf);

		job.setJarByClass(InverIndexStepOne.class);

		job.setMapperClass(InverIndexStepOneMapper.class);
		job.setReducerClass(InverIndexStopOneReducer.class);

		//指定mapper输出数据的kv类型 , 下面的可以不用写，因为map和reduce的数据类型是一致的
		/*job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);*/

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		FileInputFormat.setInputPaths(job, new Path("D:\\IDEA\\HadoopDemo\\srcdata\\inverindex\\input"));
		FileOutputFormat.setOutputPath(job, new Path("D:\\IDEA\\HadoopDemo\\srcdata\\inverindex\\output-step1"));

		boolean res = job.waitForCompletion(true);
		System.exit(res? 0 : 1);
	}
}
