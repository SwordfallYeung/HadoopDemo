package cn.itcast.bigdata.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

/**
 * @author y15079
 * @create 2018-03-19 15:26
 * @desc
 * 客户端去操作hdfs时，是有一个用户身份的
 * 默认情况下，hdfs客户端api会从jvm中获取一个参数来作为自己的用户身份，-DHADOOP_USER_NAME=hadoop
 *
 * 也可以在构造函数客户端fs对象时，通过参数传递进去
 **/
public class HdfsClientDemo {

	FileSystem fs =null;

	@Before
	public void init() throws Exception{
		Configuration conf = new Configuration();
		//conf.set("fs.defaultFS","hdfs://hadoop1:9000");
		conf.set("dfs.replication", "5");

		//拿到一个文件系统操作的客户端实例对象
		//fs = FileSystem.get(conf);
		//可以直接传入
		fs = FileSystem.get(new URI("hdfs://hadoop1:9000"), conf, "hadoop");
	}

	/**
	 * 上传文件
	 * @throws Exception
	 */
	@Test
	public void testUpload() throws Exception{
		fs.copyFromLocalFile(new Path("d://access.log"), new Path("/access.log"));
		fs.close();
	}

	@Test
	public void testDownload() throws Exception{
		fs.copyToLocalFile(new Path("/access.log.copy"), new Path("d:/"));
		fs.close();
	}

	@Test
	public void testMkdir() throws Exception{
		boolean mkdirs = fs.mkdirs(new Path("/testmkdir"));
		System.out.println(mkdirs);
	}

	@Test
	public void testDelete() throws Exception{
		boolean flag = fs.delete(new Path("/testmkdir"), true);
		System.out.println(flag);
	}

	/**
	 * 递归列出指定目录下所有子文件夹中的文件
	 * @throws Exception
	 */
	@Test
	public void testLs() throws Exception{
		RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
		while (listFiles.hasNext()){
			LocatedFileStatus fileStatus = listFiles.next();
			System.out.println("blocksize: " + fileStatus.getBlockSize());
			System.out.println("owner: " + fileStatus.getOwner());
			System.out.println("Replication: " + fileStatus.getReplication());
			System.out.println("Permission: " + fileStatus.getPermission());
			System.out.println("Name: " + fileStatus.getPath().getName());
			BlockLocation[] blockLocations = fileStatus.getBlockLocations();
			for (BlockLocation b: blockLocations){
				System.out.println("块起始偏移量： " + b.getOffset());
				System.out.println("块长度：" + b.getLength());
				String[] datanodes = b.getHosts();
				for (String a: datanodes)
				   System.out.println("datanode: " + a);
			}
			System.out.println("----------------------------");
		}
	}

	@Test
	public void testLs2() throws Exception{
		FileStatus[] listStatus = fs.listStatus(new Path("/"));
		for (FileStatus file: listStatus){
			System.out.println("Name: " + file.getPath().getName());
			System.out.println( file.isFile()?"file":"directory");
			System.out.println("----------------------------");
		}
	}

	public static void main(String[] args) throws Exception{
		//需配置启动参数 -DHADOOP_USER_NAME=hadoop
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS","hdfs://192.168.187.201:9000");
		//拿到一个文件系统操作的客户端实例对象
		FileSystem fs = FileSystem.get(new URI("hdfs://192.168.187.201:9000"), conf, "hadoop");
		fs.copyFromLocalFile(new Path("d://access.log"), new Path("/access.log"));
		fs.close();
	}
}
