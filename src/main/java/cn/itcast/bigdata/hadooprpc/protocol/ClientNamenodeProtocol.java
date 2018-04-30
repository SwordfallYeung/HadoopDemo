package cn.itcast.bigdata.hadooprpc.protocol;

/**
 * @author y15079
 * @create 2018-03-20 14:57
 * @desc
 **/
public interface ClientNamenodeProtocol {
	public static final long versionID = 1L;
	public String getMetaData(String path);
}
