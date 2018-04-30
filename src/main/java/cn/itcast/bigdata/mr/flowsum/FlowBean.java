package cn.itcast.bigdata.mr.flowsum;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author y15079
 * @create 2018-03-21 13:59
 * @desc
 **/
public class FlowBean implements WritableComparable<FlowBean> {

	private long upFlow;
	private long dFlow;
	private long sumFlow;

	//发序列化时，需要反射调用空参构造函数，所以要显示定义一个
	public FlowBean() {
	}

	public FlowBean(long upFlow, long dFlow) {
		this.upFlow = upFlow;
		this.dFlow = dFlow;
		this.sumFlow = upFlow + dFlow;
	}

	public void set(long upFlow, long dFlow) {
		this.upFlow = upFlow;
		this.dFlow = dFlow;
		this.sumFlow = upFlow + dFlow;
	}

	public long getUpFlow() {
		return upFlow;
	}

	public void setUpFlow(long upFlow) {
		this.upFlow = upFlow;
	}

	public long getdFlow() {
		return dFlow;
	}

	public void setdFlow(long dFlow) {
		this.dFlow = dFlow;
	}

	public long getSumFlow() {
		return sumFlow;
	}

	public void setSumFlow(long sumFlow) {
		this.sumFlow = sumFlow;
	}

	/**
	 * 序列化方法
	 * @param dataOutput
	 * @throws IOException
	 */
	public void write(DataOutput dataOutput) throws IOException {
		dataOutput.writeLong(upFlow);
		dataOutput.writeLong(dFlow);
		dataOutput.writeLong(sumFlow);
	}

	/**
	 * 反序列化方法
	 * 注意：反序列化的顺序跟序列化的顺序完全一致
	 * @param dataInput
	 * @throws IOException
	 */
	public void readFields(DataInput dataInput) throws IOException {
		 upFlow = dataInput.readLong();
		 dFlow = dataInput.readLong();
		 sumFlow = dataInput.readLong();
	}

	@Override
	public String toString() {
		return upFlow + "\t" + dFlow + "\t" + sumFlow;
	}

	public int compareTo(FlowBean o) {
		return this.sumFlow>o.getSumFlow()?-1:1;
	}
}
