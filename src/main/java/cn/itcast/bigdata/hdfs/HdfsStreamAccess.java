package cn.itcast.bigdata.hdfs;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URI;

/**
 * @author y15079
 * @create 2018-03-20 13:30
 * @desc
 **/
public class HdfsStreamAccess {
	FileSystem fs = null;
	Configuration conf = null;

	@Before
	public void init() throws Exception{
		conf = new Configuration();
		//拿到一个文件系统操作的客户端实例对象
		fs = FileSystem.get(conf);
		//可以直接传入uri和用户身份
		fs = FileSystem.get(new URI("hdfs://192.168.187.201:9000"), conf, "hadoop");
	}

	@Test
	public void testUpload() throws Exception{
		FSDataOutputStream outputStream = fs.create(new Path("/angelababy.love"),true);
		FileInputStream inputStream = new FileInputStream("d://angelababy.log");
		IOUtils.copy(inputStream, outputStream);
	}

	/**
	 * 通过流的方式获取hdfs上数据
	 * @throws Exception
	 */
	@Test
	public void testDownload() throws Exception{
		FSDataInputStream inputStream = fs.open(new Path("/angelababy.love"));
		FileOutputStream outputStream = new FileOutputStream("d:/helloangelbaby.log");
		IOUtils.copy(inputStream,outputStream);
	}

	/**
	 * 取自定的一部分数据
	 * @throws Exception
	 */
	@Test
	public void testRandomAccess() throws Exception{
		FSDataInputStream inputStream = fs.open(new Path("/angelababy.love"));
		inputStream.seek(12);
		FileOutputStream outputStream = new FileOutputStream("d://angelbaby.log.part2");
		IOUtils.copy(inputStream,outputStream);
	}

	/**
	 * 显示hdfs上文件的内容
	 * @throws Exception
	 */
	@Test
	public void testCat() throws Exception{
		FSDataInputStream in = fs.open(new Path("/canglaoshi_wuma.avi"));
		IOUtils.copy(in, System.out);

	}
}
