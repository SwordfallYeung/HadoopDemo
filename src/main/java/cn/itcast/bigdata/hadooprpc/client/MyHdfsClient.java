package cn.itcast.bigdata.hadooprpc.client;

import cn.itcast.bigdata.hadooprpc.protocol.ClientNamenodeProtocol;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.net.InetSocketAddress;

/**
 * @author y15079
 * @create 2018-03-20 15:06
 * @desc
 **/
public class MyHdfsClient {
	public static void main(String[] args) throws Exception{
		ClientNamenodeProtocol namenode = RPC.getProxy(ClientNamenodeProtocol.class, 1L,
				new InetSocketAddress("localhost",8888), new Configuration());
		String metaData = namenode.getMetaData("/angelababy.love");
		System.out.println(metaData);
	}
}
