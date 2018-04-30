package cn.itcast.bigdata.hadooprpc.service;

import cn.itcast.bigdata.hadooprpc.protocol.ClientNamenodeProtocol;
import cn.itcast.bigdata.hadooprpc.protocol.IUserLoginService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.Server;

/**
 * @author y15079
 * @create 2018-03-20 14:59
 * @desc
 **/
public class PublishServiceUtil {
	public static void main(String[] args) throws Exception{
		RPC.Builder builder = new RPC.Builder(new Configuration());
		builder.setBindAddress("localhost")
				.setPort(8888)
				.setProtocol(ClientNamenodeProtocol.class)
				.setInstance(new MyNameNode());

		Server server = builder.build();
		server.start();

		RPC.Builder builder2 = new RPC.Builder(new Configuration());
		builder2.setBindAddress("localhost")
				.setPort(9999)
				.setProtocol(IUserLoginService.class)
				.setInstance(new UserLoginServiceImpl());

		Server server2 = builder2.build();
		server2.start();
	}
}
