package cn.itcast.bigdata.mr.provinceflow;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;


import java.util.HashMap;

/**
 * @author y15079
 * @create 2018-03-21 17:25
 * @desc
 * Partitioner<K2,V2> 对应的是map输出kv的类型
 *
 * 根据key的前3位分成0、1、2、3、4共5个分区
 **/
public class ProvincePartitioner extends Partitioner<Text, FlowBean> {

	public static HashMap<String, Integer> proviceDict = new HashMap<String, Integer>();
    static {
    	proviceDict.put("136", 0);
    	proviceDict.put("137", 1);
		proviceDict.put("138", 2);
		proviceDict.put("139", 3);
	}

	public int getPartition(Text key, FlowBean value, int numPartitions) {
		String prefix = key.toString().substring(0, 3);
		Integer provinceId = proviceDict.get(prefix);
		return provinceId==null?4:provinceId;
	}
}
