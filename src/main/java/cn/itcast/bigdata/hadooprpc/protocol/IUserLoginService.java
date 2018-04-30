package cn.itcast.bigdata.hadooprpc.protocol;

/**
 * @author y15079
 * @create 2018-03-20 15:21
 * @desc
 **/
public interface IUserLoginService {

	public static final long versionID = 100L;
	public String login(String name, String passwd);
}
