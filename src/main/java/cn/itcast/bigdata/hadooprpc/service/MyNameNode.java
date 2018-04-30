package cn.itcast.bigdata.hadooprpc.service;

import cn.itcast.bigdata.hadooprpc.protocol.ClientNamenodeProtocol;

/**
 * @author y15079
 * @create 2018-03-20 14:52
 * @desc
 **/
public class MyNameNode implements ClientNamenodeProtocol {

	//模拟namenode的业务方法之一，查询元数据
	public String getMetaData(String path){
		return path + ": 3 - {BLK_1, BLK_2}" ;
	}
}
