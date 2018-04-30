package cn.itcast.bigdata.mr.wcdemo;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author y15079
 * @create 2018-03-21 10:09
 * @desc
 * KEYIN, VALUEIN 对应mapper输出的KEYOUT, VALUEOUT类型对应
 *
 * KEYOUT, VALUEOUT 是自定义reduce逻辑处理结果的输出数据类型
 * KEYOUT是单词
 * VALUEOUT是总次数
 **/
public class WordcountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	/**
	 * <angelababy,1><angelababy,1><angelababy,1><angelababy,1>
	 * <hello,1><hello,1><hello,1><hello,1>
	 * <banana,1><banana,1><banana,1><banana,1>
	 * @param key 入参key，是一组相同单词kv对的key
	 * @param values
	 * @param context
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		int count = 0;
		for (IntWritable value:values){
			count += value.get();
		}
		context.write(key,new IntWritable(count));
	}
}
