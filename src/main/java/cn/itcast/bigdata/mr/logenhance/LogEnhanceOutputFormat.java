package cn.itcast.bigdata.mr.logenhance;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


import java.io.IOException;


/**
 * @author y15079
 * @create 2018-03-24 2:38
 * @desc
 * maptask或者reducetask在最终输出时，先调用OutputFormat的getRecordWriter方法拿到一个RecordWriter
 * 然后再调用RecordWriter的write(k,v)方法将数据写出
 **/
public class LogEnhanceOutputFormat extends FileOutputFormat<Text, NullWritable>{
	@Override
	public RecordWriter<Text, NullWritable> getRecordWriter(TaskAttemptContext context) throws IOException, InterruptedException {
		FileSystem fs = FileSystem.get(context.getConfiguration());

		Path enhancePath = new Path("D:\\IDEA\\HadoopDemo\\srcdata\\logen\\enhancedlog\\log.dat");
		Path tocrawlPath = new Path("D:\\IDEA\\HadoopDemo\\srcdata\\logen\\tocrawl\\url.dat");

		FSDataOutputStream enhancedOs = fs.create(enhancePath);
		FSDataOutputStream tocrawlOs = fs.create(tocrawlPath);

		return new EnhanceRecordWriter(enhancedOs,tocrawlOs);
	}

	static class EnhanceRecordWriter extends RecordWriter<Text, NullWritable>{
		FSDataOutputStream enhancedOs = null;
		FSDataOutputStream tocrawlOs = null;

		public EnhanceRecordWriter(FSDataOutputStream enhancedOs, FSDataOutputStream tocrawlOs) {
			super();
			this.enhancedOs = enhancedOs;
			this.tocrawlOs = tocrawlOs;
		}

		@Override
		public void write(Text key, NullWritable value) throws IOException, InterruptedException {
			String result = key.toString();
			//如果要写出的数据是待爬的url，则写入待爬清单文件 /logenhance/tocrawl/url.dat
			if (result.contains("tocrawl")){
				tocrawlOs.write(result.getBytes());
			}else {
				//如果要写出的数据是增强日志，则写入增强日志/logenhance/enhancedlog/log.dat
				enhancedOs.write(result.getBytes());
			}
		}

		@Override
		public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
			if (enhancedOs != null){
				enhancedOs.close();
			}
			if (tocrawlOs != null){
				tocrawlOs.close();
			}
		}
	}
}
